package choubey.apurva.hotel.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import choubey.apurva.hotel.model.Room;
import choubey.apurva.hotel.service.RoomService;
import choubey.apurva.hotel.service.impl.RoomServiceImpl;

public class RoomController {

	private RoomService roomService = new RoomServiceImpl();

	public void showAvailableRooms(HttpServletRequest request, HttpServletResponse response) {

		response.setContentType("text/html");
		HttpSession session = request.getSession();

		String bookFrom = request.getParameter("bookFrom");
		String bookTill = request.getParameter("bookTill");

		List<Room> rooms = roomService.availableRooms(bookFrom, bookTill);
		if (rooms.isEmpty()) {

			try {
				PrintWriter writer = response.getWriter();
				request.getRequestDispatcher("/roomDetails").include(request, response);
				writer.println("<h3><center>Sorry, No rooms avaliable on selected dates</center></h3>");
			} catch (IOException | ServletException e) {
				e.printStackTrace();
				System.out.println("Something went wrong while fetching rooms");
			}
		} else {
			try {
				session.setAttribute("rooms", rooms);
				Date bookFromDate = Date.valueOf(bookFrom);
				Date bookTillDate = Date.valueOf(bookTill);
				if (bookFromDate.after(bookTillDate)) {
					session.setAttribute("bookFrom", bookTillDate);
					session.setAttribute("bookTill", bookFromDate);
				} else {
					session.setAttribute("bookFrom", bookFromDate);
					session.setAttribute("bookTill", bookTillDate);
				}
				request.getRequestDispatcher("/showRooms").forward(request, response);
			} catch (IOException | ServletException exception) {
				exception.printStackTrace();
				System.out.println("Something went wrong while displaying rooms");
			}
		}
	}

	public void roomsAvailableForRemoving(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Room> rooms = null;
		try {
			rooms = roomService.roomsAvailableForCancelling();
		}catch (SQLException e) {
			e.printStackTrace();
			response.getWriter().println("<center><h3 style=\"color:blue\">Couldn't fetch rooms available for removal. Please try after sometime</h3></center>");
			request.getRequestDispatcher("/index").include(request, response);
			return;
		}
		
		request.getSession().setAttribute("cancelableRooms", rooms);
		request.getRequestDispatcher("/roomsAvailableForCancellation").forward(request, response);
		return;
	}
}
