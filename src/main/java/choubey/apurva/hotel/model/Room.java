package choubey.apurva.hotel.model;

import java.io.Serializable;
import java.sql.Date;


public class Room implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8403580545187919035L;

	private String 	roomNumber;
	private String 	roomType;
	private String 	roomCapacity;
	private short	roomAvailability;
	
	public Room() {
		super();
	}

	public Room(String roomNumber, String roomType, String roomCapacity, 
			short roomAvailability) {
		super();
		this.roomNumber = roomNumber;
		this.roomType = roomType;
		this.roomCapacity = roomCapacity;
		this.roomAvailability = roomAvailability;
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

	public short getRoomAvailability() {
		return roomAvailability;
	}

	public void setRoomAvailability(short roomAvailability) {
		this.roomAvailability = roomAvailability;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Room [roomNumber=" + roomNumber + ", roomType=" + roomType + ", roomCapacity=" + roomCapacity
				+ ", roomAvailability=" + roomAvailability + "]";
	}
	
}
