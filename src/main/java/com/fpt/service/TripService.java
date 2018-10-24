package com.fpt.service;

import java.util.List;

import com.fpt.entity.Trip;

public interface TripService {
	List<Trip> findTripByRouteId(int route_id);
	List<Trip> findAll();
}
