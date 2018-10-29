package com.fpt.service;

import java.util.List;

import com.fpt.entity.Booking;
import com.fpt.entity.Bus;
import com.fpt.entity.Seat;
import com.fpt.entity.User;

public interface StaffService {
	List<Bus> getAllBus();
	List<Booking> getTicket();
	List<Seat> getAllSeatOfBus(int busId);
	boolean updateStatusSeat(int seatId, int status);
	boolean updateInfo(User user);
}
