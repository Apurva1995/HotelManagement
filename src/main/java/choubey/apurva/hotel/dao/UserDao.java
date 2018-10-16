package choubey.apurva.hotel.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import choubey.apurva.hotel.model.Booking;
import choubey.apurva.hotel.model.User;

public interface UserDao {

	public User authenticate(String userId, String password);
	public boolean save(User user);
	public void bookRoom(List<String> roomNumbers, String userAadhar, Date bookFrom, Date bookTill) throws SQLException;
	public boolean addRoom(String roomNumber, String roomType, String roomCapacity, short availability);
	public List<Booking> fetchBookings(String userAadhar) throws SQLException;
	public boolean cancelBookings(String[] bookingIds)throws SQLException;
}
