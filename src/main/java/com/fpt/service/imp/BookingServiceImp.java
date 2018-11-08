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
	public Page<Booking> getListBookingByUser(int user_id, Date date, int time, String filter, String filter_date,
			Pageable pageable) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date start_date = new Date(), end_date = new Date();
		try {
			if (filter_date.equals("-1")) {

				start_date = sdf.parse("2000-01-01");
				end_date = sdf.parse("2100-01-01");
			} else {
				start_date = end_date = sdf.parse(filter_date);

			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		// default
		if (date == null || time == 1) {
			return bookingRepository.getListBookingByUser(user_id, start_date, end_date, pageable);
		}
		// filter
		switch (filter) {
		case "waiting":
			return bookingRepository.getListBookingByUserFilterWaiting(user_id, date, time, start_date, end_date,
					pageable);
		case "expired":
			return bookingRepository.getListBookingByUserFilterExpired(user_id, date, time, start_date, end_date,
					pageable);
		case "canceled":
			return bookingRepository.getListBookingByUserFilterCanceled(user_id, date, time, start_date, end_date,
					pageable);
		default:
			return bookingRepository.getListBookingByUser(user_id, start_date, end_date, pageable);
		}
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
	public int saveBookingList(int trip_id, int user_id, User user, String[] lstSeat, String date)
			throws ParseException {
		int saveNumber = 0;

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date d = sdf.parse(date);
		Booking b = new Booking();
		Trip t = tripRepository.getOne(trip_id);

		User u = null;
		if (user_id != -1) {
			u = userRepository.getOne(user_id);
		}

		for (String s : lstSeat) {
			b = new Booking(user.getName(), user.getEmail(), user.getPhone(), user.getAddress(), u, t, d, 1, s);
			if (bookingRepository.saveAndFlush(b) != null) {
				saveNumber++;
			}
		}

		return saveNumber;
	}

}
