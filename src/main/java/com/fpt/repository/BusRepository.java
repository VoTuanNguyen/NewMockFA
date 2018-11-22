package com.fpt.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.fpt.entity.Bus;

public interface BusRepository extends JpaRepository<Bus, Integer>{
	Page<Bus> getBusByCardNumberContaining(String cardNumber, Pageable pageable);
}
