package choubey.apurva.hotel.service.impl;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import choubey.apurva.hotel.dao.UserDao;
import choubey.apurva.hotel.dao.impl.UserDaoImpl;
import choubey.apurva.hotel.model.Booking;
import choubey.apurva.hotel.model.Room;
import choubey.apurva.hotel.model.User;
import choubey.apurva.hotel.service.RoomService;
import choubey.apurva.hotel.service.UserService;
import choubey.apurva.hotel.util.RoomBookingValidator;

public class UserServiceImpl implements UserService {

	private UserDao userDao = new UserDaoImpl();
	private RoomService roomService = new RoomServiceImpl();

	@Override
	public User authenticate(String email, String password) {

		return userDao.authenticate(email, password);
	}

	@Override
	public boolean save(User user) {

		return userDao.save(user);
	}

	@Override
	public synchronized List<String> bookRoom(String[] roomNumbers, String userAadhar, Date bookFrom, Date bookTill)
			throws SQLException {
		if (roomNumbers == null || roomNumbers.length == 0)
			return null;

		List<String> result = new ArrayList<>();
		String receivedId;
		String generatedId;

		Map<String, List<Booking>> latestBookingsForSelectedRooms = roomService.latestBookings(roomNumbers);
		Set<String> availableRooms = RoomBookingValidator.checkAvailabilityOfBookedRooms(bookFrom.toString(),
				bookTill.toString(), latestBookingsForSelectedRooms);

		for (int i = 0; i < roomNumbers.length; i++) {

			if (latestBookingsForSelectedRooms.get(roomNumbers[i]) != null) {
				if (!availableRooms.contains(roomNumbers[i]))
					result.add(roomNumbers[i]);
			}
		}

		if (result.isEmpty()) {
			List<String> finalRoomList = Arrays.asList(roomNumbers);
			receivedId = userDao.bookRoom(finalRoomList, userAadhar, bookFrom, bookTill);
			generatedId = "UserId" + UUID.randomUUID().toString() + receivedId;
			result.add(generatedId);
		}
		return result;
	}

	@Override
	public boolean addRoom(String roomNumber, String roomType, String roomCapacity, short availability) {
		return userDao.addRoom(roomNumber, roomType, roomCapacity, availability);
	}

}