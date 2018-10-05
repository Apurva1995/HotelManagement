package choubey.apurva.hotel.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import choubey.apurva.hotel.model.User;
import choubey.apurva.hotel.service.UserService;
import choubey.apurva.hotel.service.impl.UserServiceImpl;

public class UserController {

	private UserService userService = new UserServiceImpl();
	
	public void login(HttpServletRequest request, HttpServletResponse response) {
		
		PrintWriter writer;
		HttpSession session;

		try {

			writer = response.getWriter();
			session = request.getSession();

			String userId = request.getParameter("userId");
			String password = request.getParameter("password");
			User user = userService.authenticate(userId, password);

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

			String userId = request.getParameter("userId");
			String userName = request.getParameter("userName");
			String password = request.getParameter("password");
			String email = request.getParameter("email");
			long mobileNumber = Long.valueOf(request.getParameter("mobileNumber"));
			double age = Double.valueOf(request.getParameter("age"));
			String sex = request.getParameter("sex");
			String aadharNumber = request.getParameter("aadharNumber");
			User user = new User(userId, userName, password, email, mobileNumber, (short)0, sex, age, aadharNumber);
			
			boolean value = userService.save(user);

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
			System.out.println("Something went wrong while authenticating");
		}
	}
}
