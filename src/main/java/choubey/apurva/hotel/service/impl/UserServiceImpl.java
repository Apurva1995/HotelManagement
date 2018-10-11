package choubey.apurva.hotel.service.impl;


import java.sql.Date;
import java.util.List;

import choubey.apurva.hotel.dao.UserDao;
import choubey.apurva.hotel.dao.impl.UserDaoImpl;
import choubey.apurva.hotel.model.User;
import choubey.apurva.hotel.service.UserService;

public class UserServiceImpl implements UserService {

	private UserDao userDao = new UserDaoImpl();

	@Override
	public User authenticate(String email, String password) {

		return userDao.authenticate(email, password);
	}

	@Override
	public boolean save(User user) {

		return userDao.save(user);
	}

	@Override
	public List<String> bookRoom(String[] roomNumbers, String userAadhar, Date bookFrom, Date bookTill) {
		if(roomNumbers == null || roomNumbers.length == 0)
			return null;
		
		userDao.bookRoom(roomNumbers, userAadhar, bookFrom, bookTill);
		return null;
	}

}