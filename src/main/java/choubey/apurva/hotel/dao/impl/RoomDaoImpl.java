package choubey.apurva.hotel.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import choubey.apurva.hotel.dao.RoomDao;
import choubey.apurva.hotel.model.Room;
import choubey.apurva.hotel.util.DBConnectionProvider;

public class RoomDaoImpl implements RoomDao {

	@Override
	public List<Room> roomDetails() {
		List<Room> rooms = new ArrayList<>();
		String query = "Select * from room where available = ?";

		try (Connection connection = DBConnectionProvider.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {

			preparedStatement.setShort(1, (short) 1);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {

				while (resultSet.next()) {

					Room room = new Room(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3),
							resultSet.getDate(4), resultSet.getDate(5), resultSet.getShort(6), resultSet.getString(7));
					rooms.add(room);
				}
			}
		} catch (SQLException exception) {

			exception.printStackTrace();
			System.out.println("Something went wrong while fetching rooms");
		}
		catch (Exception exception) {

			exception.printStackTrace();
			System.out.println("Something went wrong while fetching rooms");
		}
		return rooms;
	}
}
