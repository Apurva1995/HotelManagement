package choubey.apurva.hotel.service.impl;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import choubey.apurva.hotel.dao.RoomDao;
import choubey.apurva.hotel.dao.impl.RoomDaoImpl;
import choubey.apurva.hotel.model.Booking;
import choubey.apurva.hotel.model.Room;
import choubey.apurva.hotel.service.RoomService;
import choubey.apurva.hotel.util.RoomBookingValidator;

public class RoomServiceImpl implements RoomService {

	private RoomDao roomDao = new RoomDaoImpl();

	@Override
	public List<Room> availableRooms(String bookFrom, String bookTill) {

		List<Room> availableRooms = new ArrayList<>();

		// Fetching all rooms available for booking
		List<Room> allRooms = roomDao.roomsAvailableForBooking();

		// Fetching all the bookings done for all the rooms
		Map<String, List<Booking>> allBookings = roomDao.bookings();

		// Adding the rooms that are not booked at all to list of available
		// rooms
		for (int i = 0; i < allRooms.size(); i++) {

			if (allBookings.get(allRooms.get(i).getRoomNumber()) == null) {
				availableRooms.add(allRooms.get(i));
			}
		}

		Set<String> validatedRooms = RoomBookingValidator.checkAvailabilityOfBookedRooms(bookFrom, bookTill, allBookings);
		for (int i = 0; i < allRooms.size(); i++) {

			if (validatedRooms.contains(allRooms.get(i).getRoomNumber()))
				availableRooms.add(allRooms.get(i));
		}

		return availableRooms;
	}

	

	@Override
	public Map<String, List<Booking>> latestBookings(String[] roomNumbers) {
		return roomDao.latestBookings(roomNumbers);
	}



	@Override
	public List<Room> roomsAvailableForCancelling() throws SQLException {
		return roomDao.roomsAvailableForCancelling();
	}

}
