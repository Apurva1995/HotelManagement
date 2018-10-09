package choubey.apurva.hotel.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import choubey.apurva.hotel.dao.UserDao;
import choubey.apurva.hotel.model.Room;
import choubey.apurva.hotel.model.User;
import choubey.apurva.hotel.util.DBConnectionProvider;

public class UserDaoImpl implements UserDao {

	@Override
	public User authenticate(String email, String password) {

		String query = "select * from user where email = ? and password = ?";

		try (Connection connection = DBConnectionProvider.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {

			preparedStatement.setString(1, email);
			preparedStatement.setString(2, password);

			try (ResultSet resultSet = preparedStatement.executeQuery()) {

				if (resultSet.next()) {

					User user = new User();
					user.setAadharNumber(resultSet.getString(1));
					user.setUserName(resultSet.getString(2));
					user.setEmail(resultSet.getString(4));
					user.setMobileNumber(resultSet.getLong(5));
					user.setSex(resultSet.getString(6));
					user.setAge(resultSet.getDouble(7));
					user.setIsAdmin(resultSet.getShort(8));
					return user;
				}
			}
		} catch (SQLException exception) {

			exception.printStackTrace();
			System.out.println("Something went wrong while authentication");
		}

		return null;
	}

	@Override
	public boolean save(User user) {

		String query = "insert into user(aadharNumber, name, password, email, mobile, sex, age, isAdmin) values(?,?,?,?,?,?,?,?)";

		try (Connection connection = DBConnectionProvider.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {

			preparedStatement.setString(1, user.getAadharNumber());
			preparedStatement.setString(2, user.getUserName());
			preparedStatement.setString(3, user.getPassword());
			preparedStatement.setString(4, user.getEmail());
			preparedStatement.setLong(5, user.getMobileNumber());
			preparedStatement.setString(6, user.getSex());
			preparedStatement.setDouble(7, user.getAge());
			preparedStatement.setShort(8, user.getIsAdmin());

			if (preparedStatement.executeUpdate() == 1)
				return true;

		} catch (SQLException exception) {

			exception.printStackTrace();
			System.out.println("Something went wrong while saving data to database");
		}

		return false;
	}

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
