package choubey.apurva.hotel.model;

import java.sql.Date;

public class Booking {
	
	private long 		bookingId;
	private String		roomNumber;
	private Date		bookedFrom;
	private Date		bookedTill;
	private String		userAadhar;
	
	public Booking() {
		super();
	}

	public Booking(long bookingId, String roomNumber, Date bookedFrom, Date bookedTill, String userAadhar) {
		super();
		this.bookingId = bookingId;
		this.roomNumber = roomNumber;
		this.bookedFrom = bookedFrom;
		this.bookedTill = bookedTill;
		this.userAadhar = userAadhar;
	}

	public long getBookingId() {
		return bookingId;
	}

	public void setBookingId(long bookingId) {
		this.bookingId = bookingId;
	}

	public String getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}

	public Date getBookedFrom() {
		return bookedFrom;
	}

	public void setBookedFrom(Date bookedFrom) {
		this.bookedFrom = bookedFrom;
	}

	public Date getBookedTill() {
		return bookedTill;
	}

	public void setBookedTill(Date bookedTill) {
		this.bookedTill = bookedTill;
	}

	public String getUserAadhar() {
		return userAadhar;
	}

	public void setUserAadhar(String userAadhar) {
		this.userAadhar = userAadhar;
	}

	@Override
	public String toString() {
		return "Booking [bookingId=" + bookingId + ", roomNumber=" + roomNumber + ", bookedFrom=" + bookedFrom
				+ ", bookedTill=" + bookedTill + ", userAadhar=" + userAadhar + "]";
	}
}
