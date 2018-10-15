package choubey.apurva.hotel.dao;

import java.util.List;
import java.util.Map;

import choubey.apurva.hotel.model.Booking;
import choubey.apurva.hotel.model.Room;

public interface RoomDao {
	public List<Room> roomsAvailableForBooking();
	public Map<String, List<Booking>> bookings();
	public Map<String, List<Booking>> latestBookings(String[] roomNumbers);
}
