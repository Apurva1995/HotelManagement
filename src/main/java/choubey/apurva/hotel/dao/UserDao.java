package choubey.apurva.hotel.dao;

import java.sql.Date;
import java.util.List;

import choubey.apurva.hotel.model.Room;
import choubey.apurva.hotel.model.User;

public interface UserDao {

	public User authenticate(String userId, String password);
	public boolean save(User user);
	public List<String> bookRoom(String[] roomNumbers, String userAadhar, Date bookFrom, Date bookTill);
	public boolean addRoom(String roomNumber, String roomType, String roomCapacity, short availability);
}
