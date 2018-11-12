package com.fpt.service.imp;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fpt.entity.Booking;
import com.fpt.entity.Bus;
import com.fpt.entity.Seat;
import com.fpt.repository.BookingRepository;
import com.fpt.repository.BusRepository;
import com.fpt.repository.SeatRepository;
import com.fpt.service.StaffService;

@Service
public class StaffServiceImp implements StaffService {

	@Autowired
	private BusRepository busRepository;
	@Autowired
	private BookingRepository bookingRepository;
	@Autowired
	private SeatRepository seatRepository;
	
	@Override
	public Page<Bus> getBus(String cardNumber, Pageable pageable) {
		return busRepository.getBus(cardNumber, pageable);
	}

	@Override
	public List<Booking> getTicket() {
		return bookingRepository.findAll();
	}

	@Override
	public List<Seat> getAllSeatOfBus(String cardNumber) {
		return seatRepository.getAllSeatByBusId(cardNumber);
	}

	@Override
	public boolean updateStatusSeat(int seatId, int status) {
		Seat s = seatRepository.findById(seatId);
		s.setStatus(status);
		
		Seat s1 = seatRepository.saveAndFlush(s);
		return s1 != null ? true : false;
	}

	@Override
	public Page<Booking> getBooking(String search, Date start_date, Date end_date, Pageable pageable) {
		return bookingRepository.getListBooking(start_date, end_date, search, pageable);
	}

}
