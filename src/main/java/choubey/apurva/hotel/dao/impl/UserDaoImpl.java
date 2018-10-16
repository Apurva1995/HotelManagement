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
import choubey.apurva.hotel.model.Booking;
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
	public void bookRoom(List<String> roomNumbers, String userAadhar, Date bookFrom, Date bookTill)
			throws SQLException {
		String query = "Insert into booking (roomNumber, bookedFrom, bookedTill, userAadhar) values (?,?,?,?)";

		try (Connection connection = DBConnectionProvider.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			int count = 1;
			for (int i = 0; i < roomNumbers.size(); i++) {

				preparedStatement.setString(count++, roomNumbers.get(i));
				preparedStatement.setDate(count++, bookFrom);
				preparedStatement.setDate(count++, bookTill);
				preparedStatement.setString(count++, userAadhar);

				if(preparedStatement.executeUpdate() != 1) 
					throw new SQLException();
				
				count = 1;
			}
		}
		return;
	}

	@Override
	public boolean addRoom(String roomNumber, String roomType, String roomCapacity, short availability) {

		String query = "Insert into room values(?,?,?,?)";

		try (Connection connection = DBConnectionProvider.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {

			preparedStatement.setString(1, roomNumber);
			preparedStatement.setString(2, roomType);
			preparedStatement.setString(3, roomCapacity);
			preparedStatement.setShort(4, availability);

			if (preparedStatement.executeUpdate() == 1)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Something went wrong while adding room");
		}

		return false;
	}

	@Override
	public List<Booking> fetchBookings(String userAadhar) throws SQLException {
		
		String query = "select * from booking where userAadhar = ?";
		List<Booking> bookings = new ArrayList<>();
		
		try (Connection connection = DBConnectionProvider.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			
			preparedStatement.setString(1, userAadhar);
			try(ResultSet resultSet = preparedStatement.executeQuery()) {
				
				while(resultSet.next()) {
					
					Booking booking = new Booking(resultSet.getLong(1), resultSet.getString(2), resultSet.getDate(3), resultSet.getDate(4), resultSet.getString(5));
					bookings.add(booking);
				}
			}
			
		}
		
		return bookings;
	}

	@Override
	public boolean cancelBookings(String[] bookingIds) throws SQLException {
		
		String query = "Delete from booking where id=?";
		
		try (Connection connection = DBConnectionProvider.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			
			for(int i=0;i<bookingIds.length;i++) {
				
				preparedStatement.setLong(1, Long.valueOf(bookingIds[i]));
				preparedStatement.addBatch();
			}
			
			connection.setAutoCommit(false);
			connection.setSavepoint();
			int result[] = preparedStatement.executeBatch();
			for(int i=0;i<result.length;i++) {
				
				if(result[i] != 1) {
					
					connection.rollback();
					throw new SQLException();
				}
			}
			connection.commit();
		}
		
		return true;
	}

}
