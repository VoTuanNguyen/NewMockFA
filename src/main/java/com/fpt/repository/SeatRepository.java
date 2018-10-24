package com.fpt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fpt.entity.Seat;

public interface SeatRepository extends JpaRepository<Seat, Integer> {
	@Query("SELECT s FROM Seat s WHERE s.bus.id = :bus_id AND s.status = 0")
	List<Seat> getSeatNotAvailableByBusId(@Param("bus_id") int bus_id);
}
