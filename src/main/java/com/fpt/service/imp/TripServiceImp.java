package com.fpt.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpt.entity.Trip;
import com.fpt.repository.TripRepository;
import com.fpt.service.TripService;

@Service
public class TripServiceImp implements TripService{
	@Autowired
	private TripRepository tripRepository;
	
	@Override
	public List<Trip> findTripByRouteId(int route_id) {
		return tripRepository.findTripByRouteId(route_id);
	}

	@Override
	public List<Trip> findAll() {
		return tripRepository.findAll();
	}
}
