package choubey.apurva.hotel.service;

import java.util.List;

import choubey.apurva.hotel.model.Room;

public interface RoomService {

	public List<Room> roomDetails(String bookFrom, String bookTill);
}
