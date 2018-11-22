package com.fpt.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.fpt.entity.Booking;
import com.fpt.entity.Seat;
import com.fpt.model.Message;
import com.fpt.model.RealTimeBooking;
import com.fpt.service.BookingService;
import com.fpt.service.SeatService;

@Controller
public class WebSocketController {

	@Autowired
	private SeatService seatService;

	@Autowired
	private BookingService bookingService;

	@MessageMapping("/realtime")
	@SendTo("/realtime/seat")
	public Message greeting(RealTimeBooking real) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = sdf.parse(real.getDate());

		List<Booking> booking = bookingService.getBookingByDT(real.getTrip_id(), date);
		List<Seat> seat = seatService.getSeatNotAvailableByBusId(real.getBus_id());

		String result = "";
		for (Seat s : seat) {
			result += s.getSeatNumber() + "-";
		}

		for (Booking b : booking) {
			result += b.getSeatNumber() + "-";
		}
		
		if (!result.equals("")) {
			result = result.substring(0, result.length() - 1);// remove character - the end of string result
		}
		return new Message(result);
	}

}
