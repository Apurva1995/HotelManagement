package choubey.apurva.hotel.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
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

	public void roomDetails(HttpServletRequest request, HttpServletResponse response) {

		response.setContentType("text/html");
		HttpSession session = request.getSession();

		String bookFrom = request.getParameter("bookFrom");
		String bookTill = request.getParameter("bookTill");

		List<Room> rooms = roomService.roomDetails(bookFrom, bookTill);
		if (rooms.isEmpty()) {

			try {
				PrintWriter writer = response.getWriter();
				request.getRequestDispatcher("/roomDetails").include(request, response);
				writer.println("<h3><center>No rooms avaliable within selected period.</center></h3>");
			} catch (IOException | ServletException e) {
				e.printStackTrace();
				System.out.println("Something went wrong while fetching rooms");
			}
		} else {
			try {
				session.setAttribute("rooms", rooms);
				Date bookFromDate = Date.valueOf(bookFrom);
				Date bookTillDate = Date.valueOf(bookTill);
				session.setAttribute("bookFrom", bookFromDate);
				session.setAttribute("bookTill", bookTillDate);
				request.getRequestDispatcher("/showRooms").forward(request, response);
			} catch (IOException | ServletException exception) {
				exception.printStackTrace();
				System.out.println("Something went wrong while displaying rooms");
			}
		}
	}
}
