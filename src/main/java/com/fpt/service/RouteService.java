package com.fpt.service;

import java.util.List;

import com.fpt.entity.Route;

public interface RouteService{
	List<Route> findAll();
	List<Route> findDistinct();
	List<Route> findDest(int id);
}
