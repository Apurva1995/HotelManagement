package choubey.apurva.hotel.util;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import choubey.apurva.hotel.controller.RoomController;
import choubey.apurva.hotel.controller.UserController;

/**
 * Servlet implementation class ServiceFrontController
 */
@WebServlet(description = "Front Controller", urlPatterns = { "/controller/*" })
public class ServiceFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ServiceFrontController() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getSession().getAttribute("user") == null) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}
		UserController userController;
		RoomController roomController;
		String methodName;
		String requestPath = request.getRequestURI();
		methodName = requestPath.substring(requestPath.lastIndexOf('/') + 1);
		requestPath = requestPath.substring(0, requestPath.lastIndexOf('/'));
		requestPath = requestPath.substring(requestPath.lastIndexOf('/') + 1);

		response.setContentType("text/html");

		if ("user".equals(requestPath)) {
			userController = (UserController) ControllerObjectProvider.getControllerObject(requestPath);

			switch (methodName) {
			case "login":
				request.getRequestDispatcher("/index").forward(request, response);
				break;
			case "register":
				request.getRequestDispatcher("/register").forward(request, response);
				break;
			case "availableRooms":
				request.getRequestDispatcher("/showRooms").forward(request, response);
				break;
			case "book":
				if (request.getSession().getAttribute("rooms") == null)
					request.getRequestDispatcher("/roomDetails").forward(request, response);
				else
					request.getRequestDispatcher("/showRooms").forward(request, response);
				break;
			case "bookings":
				userController.userBookings(request, response);
				break;
			case "cancel":
				userController.userBookings(request, response);
				break;
			case "addRoom":
				request.getRequestDispatcher("/addRoom").forward(request, response);
				break;
			case "logout":
				userController.logout(request, response);
				break;
			}
		}
		else {
			roomController = (RoomController) ControllerObjectProvider.getControllerObject(requestPath);
			switch (methodName) {
			case "availableForRemoving":
				roomController.roomsAvailableForRemoving(request, response);
				break;
			}
		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String methodName;
		String requestPath = request.getRequestURI();
		methodName = requestPath.substring(requestPath.lastIndexOf('/') + 1);
		requestPath = requestPath.substring(0, requestPath.lastIndexOf('/'));
		requestPath = requestPath.substring(requestPath.lastIndexOf('/') + 1);
		invokeRespectiveController(request, response, requestPath, methodName);
	}

	private void invokeRespectiveController(HttpServletRequest request, HttpServletResponse response,
			String requestPath, String methodName) {
		UserController userController;
		RoomController roomController;

		try {
			if ("user".equals(requestPath)) {
				userController = (UserController) ControllerObjectProvider.getControllerObject(requestPath);
				invokeUserControllerMethod(request, response, userController, methodName);
			}
			else if ("room".equals(requestPath)) {
				roomController = (RoomController) ControllerObjectProvider.getControllerObject(requestPath);
				invokeRoomControllerMethod(request, response, roomController, methodName);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Something went wrong while serving the request");
		}
	}

	private boolean validateUser(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		if (request.getSession().getAttribute("user") == null) {
			request.getRequestDispatcher("/login").include(request, response);
			response.getWriter().println("<Center><h3>Please login first</h3></Center>");
			return false;
		}
		return true;
	}

	private void invokeUserControllerMethod(HttpServletRequest request, HttpServletResponse response,
			UserController userController, String methodName) throws IOException, ServletException {

		response.setContentType("text/html");

		switch (methodName) {

		case "login":
			userController.login(request, response);
			break;
		case "register":
			userController.register(request, response);
			break;
		case "book":
			if (validateUser(request, response))
				userController.bookRoom(request, response);
			break;
		case "addRoom":
			userController.addRoom(request, response);
			break;
		case "cancel":
			userController.cancelBooking(request, response);
			break;
		}
	}
	
	private void invokeRoomControllerMethod(HttpServletRequest request, HttpServletResponse response,
			RoomController roomController, String methodName) {
		
		switch (methodName) {

		case "availableRooms":
			roomController.showAvailableRooms(request, response);
			break;
		}
	}
}
