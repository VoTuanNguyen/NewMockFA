package com.fpt.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fpt.entity.Booking;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
	@Query("SELECT b FROM Booking b WHERE b.trip.id = :trip_id AND b.date = :date AND b.status = 1")
	List<Booking> getBookingInfoByDT(@Param("trip_id") int trip_id, @Param("date") Date date);

	@Query("SELECT b FROM Booking b WHERE b.user.id = :user_id AND b.date >= :start_date AND b.date <= :end_date ORDER BY b.id DESC")
	Page<Booking> getListBookingByUser(@Param("user_id") int user_id, @Param("start_date") Date start_date, @Param("end_date") Date end_date, Pageable pageable);

	@Query("SELECT b FROM Booking b WHERE b.user.id = :user_id AND (b.date > :date OR (b.date = :date AND CAST(b.trip.time as int) > :time)) "
			+ "AND b.status = 1 AND b.date >= :start_date AND b.date <= :end_date ORDER BY b.id DESC")
	Page<Booking> getListBookingByUserFilterWaiting(@Param("user_id") int user_id, @Param("date") Date date,
			@Param("time") int time, @Param("start_date") Date start_date, @Param("end_date") Date end_date, Pageable pageable);

	@Query("SELECT b FROM Booking b WHERE b.user.id = :user_id AND (b.date < :date OR (b.date = :date AND CAST(b.trip.time as int) < :time)) "
			+ "AND b.date >= :start_date AND b.date <= :end_date ORDER BY b.id DESC")
	Page<Booking> getListBookingByUserFilterExpired(@Param("user_id") int user_id, @Param("date") Date date,
			@Param("time") int time, @Param("start_date") Date start_date, @Param("end_date") Date end_date, Pageable pageable);

	@Query("SELECT b FROM Booking b WHERE b.user.id = :user_id AND b.status = 0 AND (b.date > :date OR (b.date = :date AND CAST(b.trip.time as int) > :time)) "
			+ "AND b.date >= :start_date AND b.date <= :end_date ORDER BY b.id DESC")
	Page<Booking> getListBookingByUserFilterCanceled(@Param("user_id") int user_id, @Param("date") Date date,
			@Param("time") int time, @Param("start_date") Date start_date, @Param("end_date") Date end_date, Pageable pageable);
}
