package choubey.apurva.hotel.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import choubey.apurva.hotel.dao.RoomDao;
import choubey.apurva.hotel.model.Booking;
import choubey.apurva.hotel.model.Room;
import choubey.apurva.hotel.util.DBConnectionProvider;

public class RoomDaoImpl implements RoomDao {

	@Override
	public List<Room> roomsAvailableForBooking() {
		List<Room> rooms = new ArrayList<>();
		String query = "Select * from room where available = ?";

		try (Connection connection = DBConnectionProvider.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {

			preparedStatement.setShort(1, (short) 1);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {

				while (resultSet.next()) {

					Room room = new Room(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3),
							(short) resultSet.getShort(4));
					rooms.add(room);
				}
			}
		} catch (SQLException exception) {

			exception.printStackTrace();
			System.out.println("Something went wrong while fetching rooms");
		} catch (Exception exception) {

			exception.printStackTrace();
			System.out.println("Something went wrong while fetching rooms");
		}
		return rooms;
	}

	@Override
	public Map<String, List<Booking>> bookings() {

		Map<String, List<Booking>> bookedRooms = new HashMap<>();
		String query = "Select roomNumber, bookedFrom, bookedTill from booking";

		try (Connection connection = DBConnectionProvider.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {

			try (ResultSet resultSet = preparedStatement.executeQuery()) {

				while (resultSet.next()) {

					if (bookedRooms.get(resultSet.getString(1)) == null) {

						List<Booking> bookings = new ArrayList<>();
						bookings.add(new Booking(0L, resultSet.getString(1), resultSet.getDate(2), resultSet.getDate(3),
								null));
						bookedRooms.put(resultSet.getString(1), bookings);
					} else {
						bookedRooms.get(resultSet.getString(1)).add(new Booking(0L, resultSet.getString(1),
								resultSet.getDate(2), resultSet.getDate(3), null));
					}
				}
			}

		} catch (SQLException exception) {

			exception.printStackTrace();
			System.out.println("Something went wrong while fetching rooms");
		} catch (Exception exception) {

			exception.printStackTrace();
			System.out.println("Something went wrong while fetching rooms");
		}
		return bookedRooms;
	}

	@Override
	public Map<String, List<Booking>> latestBookings(String[] roomNumbers) {

		Map<String, List<Booking>> latestBookings = new HashMap<>();
		String query = "Select roomNumber, bookedFrom, bookedTill from booking where roomNumber = ?";

		try (Connection connection = DBConnectionProvider.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {

			for (int i = 0; i < roomNumbers.length; i++) {

				preparedStatement.setString(1, roomNumbers[i]);
				try (ResultSet resultSet = preparedStatement.executeQuery()) {

					while (resultSet.next()) {

						if (latestBookings.get(resultSet.getString(1)) == null) {

							List<Booking> bookings = new ArrayList<>();
							bookings.add(new Booking(0L, resultSet.getString(1), resultSet.getDate(2),
									resultSet.getDate(3), null));
							latestBookings.put(resultSet.getString(1), bookings);
						} else {
							latestBookings.get(resultSet.getString(1)).add(new Booking(0L, resultSet.getString(1),
									resultSet.getDate(2), resultSet.getDate(3), null));
						}
					}
				}
			}

		} catch (SQLException exception) {

			exception.printStackTrace();
			System.out.println("Something went wrong while fetching rooms");
		} catch (Exception exception) {

			exception.printStackTrace();
			System.out.println("Something went wrong while fetching rooms");
		}

		return latestBookings;
	}

	@Override
	public List<Room> roomsAvailableForCancelling() throws SQLException {

		String query = "Select * from room where available=?";
		List<Room> rooms = new ArrayList<>();

		try (Connection connection = DBConnectionProvider.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {

			preparedStatement.setShort(1, (short) 1);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {

				while (resultSet.next()) {

					Room room = new Room(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getShort(4));
					rooms.add(room);
				}
			}
		}
		return rooms;
	}
}
