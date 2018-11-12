package com.fpt.service.imp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fpt.entity.Booking;
import com.fpt.entity.Seat;
import com.fpt.entity.Trip;
import com.fpt.entity.User;
import com.fpt.model.Message;
import com.fpt.repository.BookingRepository;
import com.fpt.repository.SeatRepository;
import com.fpt.repository.TripRepository;
import com.fpt.repository.UserRepository;
import com.fpt.service.BookingService;

@Service
public class BookingServiceImp implements BookingService {
	@Autowired
	private BookingRepository bookingRepository;

	@Autowired
	private SeatRepository seatRepository;

	@Autowired
	private TripRepository tripRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public List<Booking> getBookingByDT(int trip_id, Date date) {
		return bookingRepository.getBookingInfoByDT(trip_id, date);
	}

	@Override
	public Page<Booking> getListBookingByUser(int user_id, String start_date, String end_date, Pageable pageable) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date startdate = new Date(), enddate = new Date();
		try {
			if (start_date.equals("-1") && end_date.equals("-1")) {
				startdate = sdf.parse("2000-01-01");
				enddate = sdf.parse("2100-01-01");
			} else {
				startdate = sdf.parse(start_date);
				enddate = sdf.parse(end_date);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return bookingRepository.getListBookingByUser(user_id, startdate, enddate, pageable);
	}

	@Override
	public boolean updateStatus(int id, int status) {
		Booking b = bookingRepository.getOne(id);
		b.setStatus(status);
		Booking bs = bookingRepository.saveAndFlush(b);
		return bs != null;
	}

	@Override
	public Message checkBeforeBooking(int trip_id, Date date, String[] lstSeat, int bus_id) {
		String result = "";
		List<Booking> lstBooking = bookingRepository.getBookingInfoByDT(trip_id, date);
		List<Seat> lstSeatNA = seatRepository.getSeatNotAvailableByBusId(bus_id);

		// all seat available and no one has booked
		if ((lstBooking == null || lstBooking.size() == 0) && (lstSeatNA == null || lstSeatNA.size() == 0)) {
			return new Message("OK");
		}

		// all seat is available
		if (lstSeatNA == null || lstSeatNA.size() == 0) {
			for (Booking b : lstBooking) {
				for (String s : lstSeat) {
					if (b.getSeatNumber().equals(s)) {
						result += s + ",";
						break;
					}
				}
			}
		}

		// no one has booked
		if (lstBooking == null || lstBooking.size() == 0) {
			for (Seat seat : lstSeatNA) {
				for (String s : lstSeat) {
					if (s.indexOf(seat.getSeatNumber()) != -1) {
						result += s + ",";
						break;
					}
				}
			}
		}

		// has seat n/a and has many people booked on trip in date ...
		if (!(lstBooking == null || lstBooking.size() == 0) && !(lstSeatNA == null || lstSeatNA.size() == 0)) {

			// handling duplicate, no necessary :))
			for (Booking b : lstBooking) {
				for (Seat s : lstSeatNA) {
					if (b.getSeatNumber().indexOf(s.getSeatNumber()) != -1) {
						// remove duplicate
						lstBooking.remove(b);
					}
				}
			}
			// push to result
			for (Booking b : lstBooking) {
				for (String s : lstSeat) {
					if (b.getSeatNumber().equals(s)) {
						result += s + ",";
						break;
					}
				}
			}

			for (Seat seat : lstSeatNA) {
				for (String s : lstSeat) {
					if (s.indexOf(seat.getSeatNumber()) != -1) {
						result += s + ",";
						break;
					}
				}
			}
		}

		if (result.equals("")) {// all seat is already, customer can book it
			return new Message("OK");
		}

		result = result.substring(0, result.length() - 1); // trim character "," in last string
		return new Message(result); // return array contain seat invalid
	}

	// save booking for customer
	@Override
	public int saveBookingList(int trip_id, int user_id, User user, String[] lstSeat, String date, String cardNumber)
			throws ParseException {
		int saveNumber = 0;

		if (cardNumber.equals("-1")) {
			return 0;
		}

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date d = sdf.parse(date);
		Booking b = new Booking();
		Trip t = tripRepository.getOne(trip_id);

		User u = null;
		if (user_id != -1) {
			u = userRepository.getOne(user_id);
		}

		for (String s : lstSeat) {
			b = new Booking(user.getName(), user.getEmail(), user.getPhone(), user.getAddress(), u, t, d, 1, s,
					cardNumber);
			if (bookingRepository.saveAndFlush(b) != null) {
				saveNumber++;
			}
		}

		return saveNumber;
	}

	@Override
	public Booking getOne(int id) {
		return bookingRepository.getOne(id);
	}

}
