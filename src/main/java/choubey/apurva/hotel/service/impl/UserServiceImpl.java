package choubey.apurva.hotel.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import choubey.apurva.hotel.dao.UserDao;
import choubey.apurva.hotel.dao.impl.UserDaoImpl;
import choubey.apurva.hotel.model.Room;
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
	public List<Room> roomDetails(String bookFrom, String bookTill) {

		List<Room> rooms = userDao.roomDetails();
		List<Room> finalListOfRoom = new ArrayList<>();
		try {
			for (Room room : rooms) {

				if (room.getRoomBookedFrom() == null)
					finalListOfRoom.add(room);
				else {

					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Date bookFromDate = formatter.parse(bookFrom);
					Date bookTillDate = formatter.parse(bookTill);
					Date roomBookedFrom = room.getRoomBookedFrom();
					Date roomBookedTill = room.getRoomBookedTill();
					if(!(bookTillDate.after(roomBookedFrom) || bookTillDate.equals(roomBookedFrom) || bookFromDate.before(roomBookedTill) || bookFromDate.equals(roomBookedTill)))
						finalListOfRoom.add(room);
				}
			}
		} catch (ParseException exception) {

			exception.printStackTrace();
			System.out.println("Something went wrong while parsing String to Date");
		}
		return finalListOfRoom;
	}
}