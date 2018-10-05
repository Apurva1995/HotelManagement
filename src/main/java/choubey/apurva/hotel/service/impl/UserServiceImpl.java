package choubey.apurva.hotel.service.impl;

import choubey.apurva.hotel.dao.UserDao;
import choubey.apurva.hotel.dao.impl.UserDaoImpl;
import choubey.apurva.hotel.model.User;
import choubey.apurva.hotel.service.UserService;

public class UserServiceImpl implements UserService{

	private UserDao userDao = new UserDaoImpl();
	
	@Override
	public User authenticate(String userId, String password) {
		
		return userDao.authenticate(userId, password);
	}

	@Override
	public boolean save(User user) {
		
		return userDao.save(user);
	}
}
