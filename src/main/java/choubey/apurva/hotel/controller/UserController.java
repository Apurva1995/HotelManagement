package choubey.apurva.hotel.controller;

import choubey.apurva.hotel.service.UserService;
import choubey.apurva.hotel.service.impl.UserServiceImpl;

public class UserController {

	private UserService userService = new UserServiceImpl();
	
	public boolean login(String userId, String password) {
		
		return userService.login(userId, password);
	}
}
