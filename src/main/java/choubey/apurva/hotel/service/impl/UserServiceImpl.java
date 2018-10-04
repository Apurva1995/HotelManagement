package choubey.apurva.hotel.service.impl;

import choubey.apurva.hotel.dao.UserDao;
import choubey.apurva.hotel.dao.impl.UserDaoImpl;
import choubey.apurva.hotel.service.UserService;

public class UserServiceImpl implements UserService{

	private UserDao userDao = new UserDaoImpl();
	
	public boolean login(String userId, String password) {
		
		return userDao.login(userId, password);
	}
}
