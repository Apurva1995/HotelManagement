package choubey.apurva.hotel.service;

import choubey.apurva.hotel.model.User;

public interface UserService {

	public User authenticate(String userId, String password);
	public boolean save(User user); 
}
