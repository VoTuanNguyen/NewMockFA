package com.fpt.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.fpt.entity.Booking;
import com.fpt.entity.Bus;
import com.fpt.entity.Seat;
import com.fpt.entity.User;

public interface StaffService {
	Page<Bus> getBus(String cardNumber, Pageable pageable);
	List<Booking> getTicket();
	List<Seat> getAllSeatOfBus(String cardNumber);
	boolean updateStatusSeat(int seatId, int status);
	boolean updateInfo(User user);
}
