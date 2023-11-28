package com.coderscampus.practice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderscampus.practice.domain.Truck;
import com.coderscampus.practice.repository.TruckRepository;

@Service
public class TruckService {
	@Autowired
	private TruckRepository truckRepo;


	public Truck save(Truck truck) {
		return truckRepo.save(truck);
	}
	public List<Truck> findAll(){
		return truckRepo.findAll();
	}
	public Truck findById(Long id) {
		return truckRepo.findById(id).get();
	}
	public void delete(Truck truck) {
		truckRepo.delete(truck);
	}





}
