package com.coderscampus.springwise.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.coderscampus.springwise.domain.Truck;
import com.coderscampus.springwise.service.TruckService;
@Controller
@RequestMapping("truck")
public class TruckController {
	@Autowired
	private TruckService truckService;
	
	@GetMapping("/")
	public String home(ModelMap model) {
		List<Truck> trucks = truckService.findAll();
		model.put("trucks", trucks);
		return "truck/read";
	}
	
	@GetMapping("/create")
	public String getCreate (ModelMap model) {
		Truck truck = new Truck();
		model.put("truck", truck);
		return "truck/create";
	}

	@PostMapping("/create")
	public String create(Truck truck) {
		truckService.save(truck);
		return "redirect:/truck/";
	}
// This is same mapping as Truck Home page-->left here just in case.(may need deleted)
//	@GetMapping("/read")
//	public String read(ModelMap model) {
//		List<Truck> trucks = truckService.findAll();
//		model.put("trucks", trucks);
//		return "truck/read";
//	}
	
	@GetMapping("/update/{id}")
	public String fetch(ModelMap model, @PathVariable Long id) {
		Truck truck = truckService.findById(id);
		model.put("truck", truck);
		return "truck/update";
	}
	
	@PostMapping("/update")
	public String update(Truck truck) {
		truckService.save(truck);
		return "redirect:/truck/";
	}
	
	@PostMapping("/delete")
	public String delete(Truck truck) {
		truckService.delete(truck);
		return "redirect:/truck/";
	}

}
