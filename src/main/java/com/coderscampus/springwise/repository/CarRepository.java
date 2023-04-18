package com.coderscampus.springwise.repository;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.coderscampus.springwise.domain.Car;
import com.coderscampus.springwise.service.SeedService;

@Repository
public class CarRepository {
private Map<Long,Car> cars = new LinkedHashMap<>();

	@Autowired
	private SeedService seedService;
	
	public Map<Long, Car> findAll () {
		if (cars.size() == 0) {
			cars.putAll(seedService.carCreation());
		}
		return cars;
	}

	public void save(Car car) {
		if(car.getId() != null) {
			cars.put(car.getId(), car);
		}else {
			if (cars.size() == 0) {// I copied pasted the code above since in order to crea the 10 deafults you need to go to /cars page first
				//so if i save a car before loading the car page it wouldnt save create the default 10
				cars.putAll(seedService.carCreation());
			}//test
			Long id = (long) cars.size();
			car.setId(++id);
			cars.put(id, car);
		}
	}

	public Car findCarById(Long carId) {
		Car foundCar = new Car();
		for(Map.Entry<Long, Car> car : cars.entrySet()) {
			if(car.getValue().getId() == carId) {
				foundCar = car.getValue();
				break;
			}
		}
		return foundCar;
	}

	public void delete(Car car) {
		cars.remove(car.getId());
	}

}

