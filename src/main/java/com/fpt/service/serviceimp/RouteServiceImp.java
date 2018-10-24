package com.fpt.service.serviceimp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpt.entity.Route;
import com.fpt.repository.RouteRepository;
import com.fpt.service.RouteService;

@Service
public class RouteServiceImp implements RouteService{
	@Autowired
	RouteRepository routeRepository;
	
	public List<Route> findAll(){
		return routeRepository.findAll();
	}

	@Override
	public List<Route> findDistinct() {
		return routeRepository.findDistinct();
	}

	@Override
	public List<Route> findDest(int src) {
		return routeRepository.findDest(src);
	}
}
