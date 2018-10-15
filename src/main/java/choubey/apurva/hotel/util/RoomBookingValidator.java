package choubey.apurva.hotel.util;

import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import choubey.apurva.hotel.model.Booking;

public class RoomBookingValidator {

	public static Set<String> checkAvailabilityOfBookedRooms(String bookFrom, String bookTill,
			Map<String, List<Booking>> allBookings) {

		Set<String> availableRooms = new HashSet<>();
		int i;
		Date roomBookedFrom = Date.valueOf(bookFrom);
		Date roomBookedTill = Date.valueOf(bookTill);

		for (Map.Entry<String, List<Booking>> roomBookings : allBookings.entrySet()) {

			List<Booking> bookings = roomBookings.getValue();
			for (i = 0; i < bookings.size(); i++) {

				// Business logic to check availability
				Date bookFromDate = bookings.get(i).getBookedFrom();
				Date bookTillDate = bookings.get(i).getBookedTill();

				if (bookFromDate.equals(roomBookedFrom) || bookFromDate.equals(roomBookedTill))
					break;
				else if (bookTillDate.equals(roomBookedFrom) || bookTillDate.equals(roomBookedTill))
					break;
				else if (roomBookedFrom.after(bookFromDate) && roomBookedTill.before(bookTillDate))
					break;
				else if (((bookFromDate.before(roomBookedTill) && bookFromDate.after(roomBookedFrom)
						|| bookTillDate.before(roomBookedTill) && bookTillDate.after(roomBookedFrom))))
					break;
			}
			if (i == bookings.size())
				availableRooms.add(roomBookings.getKey());
		}

		return availableRooms;
	}
}
