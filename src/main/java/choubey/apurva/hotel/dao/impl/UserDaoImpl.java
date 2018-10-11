package choubey.apurva.hotel.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.ArrayList;
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
	public synchronized List<String> bookRoom(String[] roomNumbers, String userAadhar, Date bookFrom, Date bookTill) {
		String query = "Update room SET bookedFrom=?, bookedTill=?, userAadhar=? where number=? AND userAadhar is null";
		List<String> nonAvailableRooms = new ArrayList<>();
		
		try (Connection connection = DBConnectionProvider.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			connection.setAutoCommit(false);
			int count = 1;
			for (int i = 0; i < roomNumbers.length; i++) {

				preparedStatement.setDate(count++, bookFrom);
				preparedStatement.setDate(count++, bookTill);
				preparedStatement.setString(count++, userAadhar);
				preparedStatement.setString(count++, roomNumbers[i]);
				preparedStatement.addBatch();
				count = 1;
			}
			Savepoint savepoint = connection.setSavepoint();
			int[] result = preparedStatement.executeBatch();
			for (int i = 0; i < result.length; i++) {

				if (result[i] != 1) {
					nonAvailableRooms.add(roomNumbers[i]);
				}
			}
			if(!nonAvailableRooms.isEmpty())
				connection.rollback();
			else
				connection.commit();

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Something went wrong while booking room");
		}
		return nonAvailableRooms;
	}

}
