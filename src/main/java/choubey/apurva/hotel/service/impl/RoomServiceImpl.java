package choubey.apurva.hotel.service.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import choubey.apurva.hotel.dao.RoomDao;
import choubey.apurva.hotel.dao.impl.RoomDaoImpl;
import choubey.apurva.hotel.model.Room;
import choubey.apurva.hotel.service.RoomService;

public class RoomServiceImpl implements RoomService {

	private RoomDao roomDao = new RoomDaoImpl();

	@Override
	public List<Room> roomDetails(String bookFrom, String bookTill) {

		List<Room> rooms = roomDao.roomDetails();
		List<Room> finalListOfRoom = new ArrayList<>();
		for (Room room : rooms) {

			if (room.getRoomBookedFrom() == null)
				finalListOfRoom.add(room);
			else {

				Date bookFromDate = Date.valueOf(bookFrom);
				Date bookTillDate = Date.valueOf(bookTill);
				Date roomBookedFrom = room.getRoomBookedFrom();
				Date roomBookedTill = room.getRoomBookedTill();
				if (!(bookTillDate.after(roomBookedFrom) || bookTillDate.equals(roomBookedFrom)
						|| bookFromDate.before(roomBookedTill) || bookFromDate.equals(roomBookedTill)))
					finalListOfRoom.add(room);
			}
		}
		return finalListOfRoom;
	}

}
