package com.fpt.service.serviceimp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpt.entity.Seat;
import com.fpt.repository.SeatRepository;
import com.fpt.service.SeatService;

@Service
public class SeatServiceImp implements SeatService {
	@Autowired
	private SeatRepository seatRepository;
	
	@Override
	public List<Seat> getSeatNotAvailableByBusId(int bus_id) {
		return seatRepository.getSeatNotAvailableByBusId(bus_id);
	}
	
}
