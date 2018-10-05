package choubey.apurva.hotel.dao;

import choubey.apurva.hotel.model.User;

public interface UserDao {

	public User authenticate(String userId, String password);
	public boolean save(User user);
}
