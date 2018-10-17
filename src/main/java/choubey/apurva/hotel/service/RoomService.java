package choubey.apurva.hotel.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import choubey.apurva.hotel.model.Booking;
import choubey.apurva.hotel.model.Room;

public interface RoomService {

	public List<Room> availableRooms(String bookFrom, String bookTill);
	public Map<String, List<Booking>> latestBookings(String[] roomNumbers);
	public List<Room> roomsAvailableForCancelling() throws SQLException;
}
