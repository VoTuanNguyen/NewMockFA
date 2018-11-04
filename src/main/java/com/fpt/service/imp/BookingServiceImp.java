package com.fpt.service.imp;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fpt.entity.Booking;
import com.fpt.repository.BookingRepository;
import com.fpt.service.BookingService;

@Service
public class BookingServiceImp implements BookingService {
	@Autowired
	private BookingRepository bookingRepository;

	@Override
	public List<Booking> getBookingByDT(int trip_id, Date date) {
		return bookingRepository.getBookingInfoByDT(trip_id, date);
	}

	@Override
	public Page<Booking> getListBookingByUser(int user_id, Date date, int time, String filter, Pageable pageable) {
		//default
		if (date == null || time == 1) {
			return bookingRepository.getListBookingByUser(user_id, pageable);
		}
		//filter
		switch (filter) {
		case "waiting":
			return bookingRepository.getListBookingByUserFilterWaiting(user_id, date, time, pageable);
		case "expired":
			return bookingRepository.getListBookingByUserFilterExpired(user_id, date, time, pageable);
		case "canceled":
			return bookingRepository.getListBookingByUserFilterCanceled(user_id, date, time,pageable);
		default:
			return bookingRepository.getListBookingByUser(user_id, pageable);
		}
	}

	@Override
	public boolean updateStatus(int id, int status) {
		Booking b = bookingRepository.getOne(id);
		b.setStatus(status);
		Booking bs = bookingRepository.saveAndFlush(b);
		return bs != null;
	}

}
