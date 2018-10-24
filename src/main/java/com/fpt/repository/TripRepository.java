package com.fpt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fpt.entity.Trip;

public interface TripRepository extends JpaRepository<Trip, Integer> {
	@Query("SELECT t FROM Trip t WHERE t.route.id = :route_id")
	List<Trip> findTripByRouteId(@Param("route_id") int route_id);
}
