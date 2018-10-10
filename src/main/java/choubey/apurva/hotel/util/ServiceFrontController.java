package choubey.apurva.hotel.util;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		
		System.out.println("Inside get");
		UserController userController;
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

		if ("user".equals(requestPath)) {
			userController = (UserController) ControllerObjectProvider.getControllerObject(requestPath);
			invokeUserControllerMethod(request, response, userController, methodName);
		}
	}

	private void invokeUserControllerMethod(HttpServletRequest request, HttpServletResponse response,
			UserController userController, String methodName) {

		response.setContentType("text/html");

		switch (methodName) {

		case "login":
			userController.login(request, response);
			break;
		case "register":
			userController.register(request, response);
			break;
		case "details":
			userController.roomDetails(request, response);
			break;
		}
	}

}
