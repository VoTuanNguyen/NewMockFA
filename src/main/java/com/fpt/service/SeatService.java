package com.fpt.service;

import java.util.List;

import com.fpt.entity.Seat;

public interface SeatService {
	List<Seat> getSeatNotAvailableByBusId(int bus_id);
}
