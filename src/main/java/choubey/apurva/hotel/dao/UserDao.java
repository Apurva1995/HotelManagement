package choubey.apurva.hotel.dao;

import java.util.List;

import choubey.apurva.hotel.model.Room;
import choubey.apurva.hotel.model.User;

public interface UserDao {

	public User authenticate(String userId, String password);
	public boolean save(User user);
	public List<Room> roomDetails();
}
