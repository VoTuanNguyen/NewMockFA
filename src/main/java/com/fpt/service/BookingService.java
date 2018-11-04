package com.fpt.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.fpt.entity.Booking;

public interface BookingService {
	List<Booking> getBookingByDT(int trip_id, Date date);
	Page<Booking> getListBookingByUser(int user_id, Date date, int time, String filter, Pageable pageable);
	boolean updateStatus(int id, int status);
}
