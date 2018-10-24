package com.fpt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fpt.entity.Seat;
import com.fpt.service.SeatService;

@RestController
@RequestMapping("/seat")
@CrossOrigin
public class SeatController {
	@Autowired
	private SeatService seatService;
	
	@GetMapping("/getseatnotavailable/{bus_id}")
	public List<Seat> getSeatNotAvailableByBusId(@PathVariable int bus_id){
		return seatService.getSeatNotAvailableByBusId(bus_id);
	}
}
