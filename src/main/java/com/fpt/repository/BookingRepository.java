package com.fpt.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fpt.entity.Booking;

public interface BookingRepository extends JpaRepository<Booking, Integer>{
	@Query("SELECT b FROM Booking b WHERE b.trip.id = :trip_id AND b.date = :date")
	List<Booking> getBookingInfoByDT(@Param("trip_id") int trip_id, @Param("date") Date date);
}
