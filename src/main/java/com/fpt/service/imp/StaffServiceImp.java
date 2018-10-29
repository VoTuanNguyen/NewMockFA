package com.fpt.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpt.entity.Booking;
import com.fpt.entity.Bus;
import com.fpt.entity.Seat;
import com.fpt.entity.User;
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
	public List<Bus> getAllBus() {
		return busRepository.findAll();
	}

	@Override
	public List<Booking> getTicket() {
		return bookingRepository.findAll();
	}

	@Override
	public List<Seat> getAllSeatOfBus(int busId) {
		return seatRepository.getAllSeatByBusId(busId);
	}

	@Override
	public boolean updateStatusSeat(int seatId, int status) {
		Seat s = seatRepository.findById(seatId);
		s.setStatus(status);
		
		Seat s1 = seatRepository.saveAndFlush(s);
		return s1 != null ? true : false;
	}

	@Override
	public boolean updateInfo(User user) {
		return false;
	}

}
