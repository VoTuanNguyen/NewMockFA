package com.fpt.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fpt.entity.Booking;
import com.fpt.service.BookingService;

@RestController
@RequestMapping("/booking")
@CrossOrigin
public class BookingController {
	@Autowired
	private BookingService bookingService;
	
	@GetMapping("/getbooking/{trip_id}/{string_date}")
	public List<Booking> getBookingByDT(@PathVariable int trip_id, @PathVariable String string_date) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = sdf.parse(string_date);
		return bookingService.getBookingByDT(trip_id, date);
	}
}
