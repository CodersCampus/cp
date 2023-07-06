package com.coderscampus.springwise.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderscampus.springwise.domain.Car;
import com.coderscampus.springwise.repository.CarRepository;

@Service
public class CarService {
	@Autowired
	private CarRepository carRepo;
	
	public Car save(Car car) {
		return carRepo.save(car);
	}
	public List<Car> findAll(){
		return carRepo.findAll();
	}
	public Car findById(Long id) {
		return carRepo.findById(id).get();
	}
	public void delete(Car car) {
		carRepo.delete(car);
	}

}
