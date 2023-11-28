package com.coderscampus.practice.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.coderscampus.practice.domain.Car;
import com.coderscampus.practice.service.CarService;
@Controller
@RequestMapping("car")
public class CarController {
	@Autowired
	private CarService carService;
	
	@GetMapping("/")
	public String home(ModelMap model) {
		List<Car> cars = carService.findAll();
		model.put("cars", cars);
		return "car/read";
	}
	
	@GetMapping("/create")
	public String getCreate (ModelMap model) {
		Car car = new Car();
		model.put("car", car);
		return "car/create";
	}

	@PostMapping("/create")
	public String create(Car car) {
		carService.save(car);
		return "redirect:/car/";
	}

	
	@GetMapping("/update/{id}")
	public String fetch(ModelMap model, @PathVariable Long id) {
		Car car = carService.findById(id);
		model.put("car", car);
		return "car/update";
	}
	
	@PostMapping("/update")
	public String update(Car car) {
		carService.save(car);
		return "redirect:/car/";
	}
	
	@PostMapping("/delete")
	public String delete(Car car) {
		carService.delete(car);
		return "redirect:/car/";
	}

}
