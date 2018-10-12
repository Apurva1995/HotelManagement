package choubey.apurva.hotel.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import choubey.apurva.hotel.model.User;
import choubey.apurva.hotel.service.RoomService;
import choubey.apurva.hotel.service.UserService;
import choubey.apurva.hotel.service.impl.RoomServiceImpl;
import choubey.apurva.hotel.service.impl.UserServiceImpl;

public class UserController {

	private UserService userService = new UserServiceImpl();
	private RoomService roomService = new RoomServiceImpl();

	public void login(HttpServletRequest request, HttpServletResponse response) {

		PrintWriter writer;
		HttpSession session;

		try {

			writer = response.getWriter();
			session = request.getSession();

			String email = request.getParameter("email");
			String password = request.getParameter("password");
			User user = null;
			try {
				user = userService.authenticate(email, password);
				session.setAttribute("user", user);
			} catch (Exception exception) {
				exception.printStackTrace();
				System.out.println("Something went wrong while authentication");
			}
			if (user != null) {
				session.setAttribute("user", user);
				request.getRequestDispatcher("/index").forward(request, response);
			} else {
				request.getRequestDispatcher("/login").include(request, response);
				writer.println("<center><h3>Incorrect userId or password</h3><center>");
			}
		} catch (IOException exception) {
			exception.printStackTrace();
			System.out.println("Couldn't get writter from response object");
		} catch (ServletException e) {
			e.printStackTrace();
			System.out.println("Something went wrong while authenticating");
		}
	}

	public void register(HttpServletRequest request, HttpServletResponse response) {

		PrintWriter writer;
		HttpSession session;

		try {

			writer = response.getWriter();
			session = request.getSession();

			String userName = request.getParameter("userName");
			String password = request.getParameter("password");
			String email = request.getParameter("email");
			long mobileNumber = Long.valueOf(request.getParameter("mobileNumber"));
			double age = Double.valueOf(request.getParameter("age"));
			String sex = request.getParameter("sex");
			String aadharNumber = request.getParameter("aadharNumber");
			User user = new User(userName, password, email, mobileNumber, (short) 0, sex, age, aadharNumber);
			boolean value = false;
			try {
				value = userService.save(user);
			} catch (Exception exception) {
				exception.printStackTrace();
				System.out.println("Something went wrong while saving data to database");
			}
			if (value) {
				request.getRequestDispatcher("/login").include(request, response);
				writer.println("<center><h3>Successfully signed in.<br>Login to continue</h3><center>");
			} else {
				request.getRequestDispatcher("/register").include(request, response);
				writer.println("<center><h3>Something went wrong. Please try again.</h3><center>");
			}
		} catch (IOException exception) {
			exception.printStackTrace();
			System.out.println("Couldn't get writter from response object");
		} catch (ServletException e) {
			e.printStackTrace();
			System.out.println("Something went wrong while saving data to database");
		}
	}
	
	@SuppressWarnings("static-access")
	public void bookRoom(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		HttpSession session = request.getSession();
		
		String rooms[] = (String[]) request.getParameterValues("bookedRooms");
		Date bookFrom = (Date)session.getAttribute("bookFrom");
		Date bookTill = (Date)session.getAttribute("bookTill");
		String userAadhar = ((User)session.getAttribute("user")).getAadharNumber();
		
		List<String> nonAvailableRooms = userService.bookRoom(rooms, userAadhar, bookFrom, bookTill);
		
		if(nonAvailableRooms == null) {
			request.getRequestDispatcher("/showRooms").include(request, response);
			writer.println("<Center><h3 style=\"color:blue\">Please select rooms to book</h3></center>");
			return;
		}
		else if(nonAvailableRooms.isEmpty()) {
			session.removeAttribute("rooms");
			session.removeAttribute("bookFrom");
			session.removeAttribute("bookTill");
			request.getRequestDispatcher("/showWhenBooked").include(request, response);
			if(rooms.length == 1)
				writer.println("<Center><h3 style=\"color:blue\">Room has been successfully booked</h3></center>");
			else
				writer.println("<Center><h3 style=\"color:blue\">Rooms have been successfully booked</h3></center>");
		}
		/*else {
			session.setAttribute("rooms", roomService.roomDetails(bookFrom.toString(), bookTill.toString()));
			request.setAttribute("nonAvailableRooms", nonAvailableRooms);
			request.getRequestDispatcher("/showRoomsWhenNotBooked").include(request, response);
			if(nonAvailableRooms.size() == 1)  {
				writer.println("<Center><h3 style=\"color:blue\">Room " + nonAvailableRooms.get(0) + " is already booked</h3></center>");
				writer.println("<Center><h3 style=\"color:blue\">Kindly book some other room</h3></center>");
			}
			else {
				writer.print("<Center><h3 style=\"color:blue\">Rooms "); 
				for(int i=0;i<nonAvailableRooms.size();i++) {
					
					if(i == nonAvailableRooms.size()-1)
						writer.print("<h3 style=\"color:blue\">and  </h3>"+ nonAvailableRooms.get(i));
					else
						writer.print(nonAvailableRooms.get(i) + ", ");
				}
				writer.println("have been successfully booked</h3></center>\");");
			}
		}*/
		
	}
}
