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
import com.fpt.service.PaymentService;

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

	@Autowired
	private PaymentService paymentService;

	// get list booking of trip and date
	@GetMapping("/getbooking/{trip_id}/{string_date}")
	public List<Booking> getBookingByDT(@PathVariable int trip_id, @PathVariable String string_date)
			throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = sdf.parse(string_date);
		return bookingService.getBookingByDT(trip_id, date);
	}

	// get list booking of user
	@GetMapping("/getlistbooking/{token}/{start_date}/{end_date}")
	public Page<Booking> getListBookingByUser(@PathVariable String token, @PathVariable String start_date,
			@PathVariable String end_date, Pageable pageable) throws ParseException {
		if (jwtService.validateTokenLogin(token)) {
			String username = jwtService.getUsernameFromToken(token);
			User user = accountService.getUserByUsername(username);
			return bookingService.getListBookingByUser(user.getId(), start_date, end_date, pageable);
		}
		return null;
	}

	// cancel booking
	@GetMapping("/updatestatus/{id}/{status}/{moneyrefund}")
	public Message updateStatus(@PathVariable int id, @PathVariable int status, @PathVariable double moneyrefund) {
		Booking b = bookingService.getOne(id);
		if (!paymentService.updateBalance(b.getCardNumber(), moneyrefund)) {
			return new Message("KO");
		}
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
	@PostMapping("/savebookinglist/{trip_id}/{token}/{date}/{cardNumber}")
	public Message saveBooking(@PathVariable int trip_id, @PathVariable String token, @PathVariable String date,
			@PathVariable String cardNumber, @RequestBody SaveBooking save) throws ParseException {
		int user_id = -1;
		if (!token.equals("-1")) {
			String username = jwtService.getUsernameFromToken(token);
			User user = accountSerivce.getUserByUsername(username);
			user_id = user.getId();
		}
		// balance > order money
		if (paymentService.checkBalance(save.getPayment().getId(), save.getPayment().getBalance())) {
			// process payment for order(booking ticket) before save
			if (!paymentService.updateBalance(save.getPayment().getId(), save.getPayment().getBalance())) {
				return new Message("PF");
			}
			// save booking
			return new Message(String.valueOf(bookingService.saveBookingList(trip_id, user_id, save.getUser(),
					save.getLstSeat(), date, cardNumber)));
		} else {
			return new Message("NEM"); // not enough money to payment
		}

	}
}
