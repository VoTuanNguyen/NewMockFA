package com.fpt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fpt.entity.Bus;
import com.fpt.entity.Seat;
import com.fpt.model.Message;
import com.fpt.service.StaffService;

@RestController
@CrossOrigin
@RequestMapping("/staff")
public class StaffController {

	@Autowired
	private StaffService staffService;
	
	//check access
	@GetMapping("/access")
	public Message checkAccess() {
		return new Message("access");
	}
	
	//get bus
	@GetMapping("/getbus/{cardNumber}")
	public Page<Bus> getBus(@PathVariable String cardNumber, Pageable pageable){
		if(!cardNumber.equals("ns")) {
			return staffService.getBus("%"+cardNumber+"%", pageable);
		}else {
			return staffService.getBus("%-%", pageable);
		}
	}
	
	//get seat from cardNumber
	@GetMapping("/getseat/{cardNumber}")
	public List<Seat> getSeat(@PathVariable String cardNumber){
		return staffService.getAllSeatOfBus(cardNumber);
	}
	
	//update seat status
	@GetMapping("/updateseat/{id}/{status}")
	public Message updateSeat(@PathVariable int id, @PathVariable int status) {
		return staffService.updateStatusSeat(id, status) ? new Message("OK") : new Message("KO");
	}
}
