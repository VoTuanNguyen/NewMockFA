package com.fpt.service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.fpt.entity.Booking;
import com.fpt.entity.User;
import com.fpt.model.Message;

public interface BookingService {
	List<Booking> getBookingByDT(int trip_id, Date date);
	Page<Booking> getListBookingByUser(int user_id, Date date, int time, String filter, String start_date, String end_date, Pageable pageable);
	boolean updateStatus(int id, int status);
	Message checkBeforeBooking(int trip_id, Date date, String[] lstSeat, int bus_id);
	int saveBookingList(int trip_id, int user_id, User user, String[] lstSeat, String date, String cardNumber) throws ParseException;
	Booking getOne(int id);
}
