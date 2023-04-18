package com.coderscampus.springwise.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.coderscampus.springwise.domain.Car;
import com.coderscampus.springwise.service.CarService;

@Controller
@RequestMapping("/car")
public class CarController {
	
	@Autowired
	private CarService carService;

	@GetMapping("/")
	public String indexPage() {
		return "car-index";
	}
	
	@GetMapping("/create")
	public String createCar(ModelMap model) {// added the modelmap to get access to the car object within html
		Car car = new Car();
		model.put("car", car);
		return "create-car";
	}
	
	@PostMapping("/create")
	public String createCarPost(Car car) {// I pass in the car object so that when it gets submitted a car object is passed in order to save
		carService.save(car);
		return "redirect:/car/cars";
	}

	@GetMapping("/update")
	public String editCar() {
		return "update-car";
	}
	
	@GetMapping("/cars")
    public String allCars(Model model) {
        //list with Cars
        Map<Long, Car>  carsList = carService.findAll();
        model.addAttribute("list", carsList);
        return "cars";
    }
	@GetMapping("/car/{carId}")
	public String viewCar(@PathVariable Long carId, ModelMap model) {
		Car car = carService.findCarById(carId);
		model.put("car", car);
		return "car";
	}
	@PostMapping("/car/{carId}")
	public String updateCar(Car car) {
		carService.save(car);
		return "redirect:/car/cars";
	}
	
	@PostMapping("/delete/{carId}")
	public String deleteCar (Car car) {
		carService.delete(car);
		return "redirect:/car/cars";
	}
}
