package choubey.apurva.hotel.dao.impl;

import choubey.apurva.hotel.dao.UserDao;
import choubey.apurva.hotel.model.User;

public class UserDaoImpl implements UserDao {

	@Override
	public User authenticate(String userId, String password) {
		return new User(userId, "Jhon", password, null, 0L, (short)0, null, 0.0, null);
	}

	@Override
	public boolean save(User user) {
		return true;
	}

}
