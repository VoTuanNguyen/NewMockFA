package com.fpt.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.fpt.entity.Booking;
import com.fpt.entity.Bus;
import com.fpt.entity.Seat;

public interface StaffService {
	Page<Bus> getBus(String cardNumber, Pageable pageable);
	List<Booking> getTicket();
	List<Seat> getAllSeatOfBus(String cardNumber);
	boolean updateStatusSeat(int seatId, int status);
	Page<Booking> getBooking(String search, Date start_date, Date end_date, Pageable pageable);
}
