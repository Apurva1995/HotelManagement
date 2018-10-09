package choubey.apurva.hotel.model;

import java.io.Serializable;
import java.util.Date;


public class Room implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8403580545187919035L;

	private String 	roomNumber;
	private String 	roomType;
	private String 	roomCapacity;
	private Date 	roomBookedFrom;
	private Date 	roomBookedTill;
	private short	roomAvailability;
	private String	userAadharNumber;
	
	public Room() {
		super();
	}

	public Room(String roomNumber, String roomType, String roomCapacity, Date roomBookedFrom, Date roomBookedTill,
			short roomAvailability, String	userAadharNumber) {
		super();
		this.roomNumber = roomNumber;
		this.roomType = roomType;
		this.roomCapacity = roomCapacity;
		this.roomBookedFrom = roomBookedFrom;
		this.roomBookedTill = roomBookedTill;
		this.roomAvailability = roomAvailability;
		this.userAadharNumber = userAadharNumber;
	}

	public String getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public String getRoomCapacity() {
		return roomCapacity;
	}

	public void setRoomCapacity(String roomCapacity) {
		this.roomCapacity = roomCapacity;
	}

	public Date getRoomBookedFrom() {
		return roomBookedFrom;
	}

	public void setRoomBookedFrom(Date roomBookedFrom) {
		this.roomBookedFrom = roomBookedFrom;
	}

	public Date getRoomBookedTill() {
		return roomBookedTill;
	}

	public void setRoomBookedTill(Date roomBookedTill) {
		this.roomBookedTill = roomBookedTill;
	}

	public short getRoomAvailability() {
		return roomAvailability;
	}

	public void setRoomAvailability(short roomAvailability) {
		this.roomAvailability = roomAvailability;
	}

	public String getUser() {
		return userAadharNumber;
	}

	public void setUser(String user) {
		this.userAadharNumber = user;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Room [roomNumber=" + roomNumber + ", roomType=" + roomType + ", roomCapacity=" + roomCapacity
				+ ", roomBookedFrom=" + roomBookedFrom + ", roomBookedTill=" + roomBookedTill + ", roomAvailability="
				+ roomAvailability + ", userAadharNumber=" + userAadharNumber + "]";
	}
	
}
