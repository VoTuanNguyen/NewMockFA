package com.fpt.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fpt.entity.Bus;

public interface BusRepository extends JpaRepository<Bus, Integer>{
	@Query("SELECT b FROM Bus b WHERE b.cardNumber LIKE :cardNumber")
	Page<Bus> getBus(@Param("cardNumber") String cardNumber, Pageable pageable);
}
