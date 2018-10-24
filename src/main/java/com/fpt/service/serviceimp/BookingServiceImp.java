package com.fpt.service.serviceimp;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpt.entity.Booking;
import com.fpt.repository.BookingRepository;
import com.fpt.service.BookingService;

@Service
public class BookingServiceImp implements BookingService{
	@Autowired
	private BookingRepository bookingRepository;
	
	@Override
	public List<Booking> getBookingByDT(int trip_id, Date date) {
		return bookingRepository.getBookingInfoByDT(trip_id, date);
	}
	
}
