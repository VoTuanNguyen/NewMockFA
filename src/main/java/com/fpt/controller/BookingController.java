package com.fpt.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fpt.entity.Booking;
import com.fpt.entity.User;
import com.fpt.model.Message;
import com.fpt.model.SaveBooking;
import com.fpt.service.AccountService;
import com.fpt.service.BookingService;
import com.fpt.service.JwtService;

@RestController
@RequestMapping("/booking")
@CrossOrigin
public class BookingController {
	@Autowired
	private BookingService bookingService;

	@Autowired
	private JwtService jwtService;

	@Autowired
	private AccountService accountService;

	@Autowired
	private AccountService accountSerivce;

	@GetMapping("/getbooking/{trip_id}/{string_date}")
	public List<Booking> getBookingByDT(@PathVariable int trip_id, @PathVariable String string_date)
			throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = sdf.parse(string_date);
		return bookingService.getBookingByDT(trip_id, date);
	}

	@GetMapping("/getlistbooking/{token}/{date}/{time}/{filter}")
	public Page<Booking> getListBookingByUser(@PathVariable String token, @PathVariable String date,
			@PathVariable int time, @PathVariable String filter, Pageable pageable) throws ParseException {
		if (jwtService.validateTokenLogin(token)) {
			String username = jwtService.getUsernameFromToken(token);
			User user = accountService.getUserByUsername(username);

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			if (!date.equals("1") && time != 1) {
				Date d = sdf.parse(date);
				return bookingService.getListBookingByUser(user.getId(), d, time, filter, pageable);
			} else {
				return bookingService.getListBookingByUser(user.getId(), null, time, filter, pageable);
			}
		}
		return null;
	}

	@GetMapping("/updatestatus/{id}/{status}")
	public Message updateStatus(@PathVariable int id, @PathVariable int status) {
		return bookingService.updateStatus(id, status) ? new Message("OK") : new Message("KO");
	}

	// check before save booking
	@PostMapping("/checkbeforesave/{trip_id}/{bus_id}/{date}")
	public Message checkBeforeUpdate(@PathVariable int trip_id, @PathVariable int bus_id, @PathVariable String date,
			@RequestBody String[] lstSeat) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date d = sdf.parse(date);

		return bookingService.checkBeforeBooking(trip_id, d, lstSeat, bus_id);
	}

	// save list booking for customer
	@PostMapping("savebookinglist/{trip_id}/{token}/{date}")
	public Message saveBooking(@PathVariable int trip_id, @PathVariable String token, @PathVariable String date,
			@RequestBody SaveBooking save) throws ParseException {
		int user_id = -1;
		if (!token.equals("-1")) {
			String username = jwtService.getUsernameFromToken(token);
			User user = accountSerivce.getUserByUsername(username);
			user_id = user.getId();
		}

		return new Message(String.valueOf(
				bookingService.saveBookingList(trip_id, user_id, save.getUser(), save.getLstSeat(), date)));
	}
}
