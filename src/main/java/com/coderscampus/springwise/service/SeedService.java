package com.coderscampus.springwise.service;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderscampus.springwise.domain.Airplane;
import com.coderscampus.springwise.domain.Car;
import com.coderscampus.springwise.domain.Dog;
import com.coderscampus.springwise.domain.Frog;
import com.coderscampus.springwise.domain.Truck;
import com.coderscampus.springwise.repository.AirplaneRepository;
import com.coderscampus.springwise.repository.DogRepository;
import com.coderscampus.springwise.repository.FrogRepository;
import com.coderscampus.springwise.repository.TruckRepository;

@Service
public class SeedService {
	@Autowired
	private AirplaneRepository airRepo;
	@Autowired
	private FrogRepository frogRepo;
	Map<Long, Car> cars = new LinkedHashMap<>();
	@Autowired
	private DogRepository dogRepo;
	@Autowired
	private DataFileService dataFileService;
	@Autowired
	private TruckRepository truckRepo;

	public Map<Long, Car> carCreation() {

		Random random = new Random();
		String[] motorSizes = { "Small", "Medium", "Large" };
		String[] modelNames = { "Audi", "Lamborghini", "BMW", "Volkswagen", "Kia", "Honda", "Chevrolet", "Ford",
				"Lincoln", "LandRover", "Infiniti", "Nissan", "Ram", "Toyota", "Tesla", "Subaru", "Volvo" };

		Integer[] wheelSizes = { 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20 };
		String[] transmissions = { "automatic", "manual", "hybrid" };
		String[] colors = { "Yellow", "Red", "Blue", "Orange", "Purple", "White", "Black", "Grey" };
		Integer[] years = { 2015, 2016, 2017, 2018, 2019, 2020, 2021, 2022 };
		BigDecimal[] prices = { new BigDecimal(29845), new BigDecimal(58630), new BigDecimal(75392) };

		Long i = 1L;
		while (i <= 10) {
			String motorSize = motorSizes[random.nextInt(motorSizes.length - 1)];
			String modelName = modelNames[random.nextInt(modelNames.length - 1)];
			Integer wheelSize = wheelSizes[random.nextInt(wheelSizes.length - 1)];
			String transmission = transmissions[random.nextInt(transmissions.length - 1)];
			String color = colors[random.nextInt(colors.length - 1)];
			Integer year = years[random.nextInt(years.length - 1)];
			BigDecimal price = prices[random.nextInt(prices.length - 1)];
			cars.put(i, new Car(i, motorSize, modelName, wheelSize, transmission, color, year, price));
			i++;
		}
		return cars;

	}

	public void populateData() {

		Random random = new Random();
		Airplane airplane = new Airplane();
		airplane.setModel("747");
		airplane.setMake("Boeing");
		airplane.setColor("Fusia");
		airRepo.save(airplane);

		String[] frogName = {"kermit", "frogger", "mr. toad", "tree frog"};
		String[] frogSpecies = {"blue", "green", "pink", "purple"};
		String[] frogAge = {"young", "old", "young", "old"};

		for (int i = 0; i < 10; i++) {

			Frog frog = new Frog();

			frog.setName(frogName[random.nextInt(frogName.length - 1)]);
			frog.setAge(frogAge[random.nextInt(frogAge.length - 1)]);
			frog.setSpecies(frogSpecies[random.nextInt(frogSpecies.length - 1)]);

			frogRepo.save(frog);

		}

		String[] dogName = {"Coco", "Woof", "Shrimp", "Nuts", "Rover", "Wilber"};
		String[] dogBreed = {"Shepperd", "Chihuahua", "Pomeranian", "Poodle"};
		String[] dogAge = {"young", "old", "young", "old"};

		for (int i = 0; i < 10; i++) {

			Dog dog = new Dog();

			dog.setName(dogName[random.nextInt(dogName.length - 1)]);
			dog.setAge(dogAge[random.nextInt(dogAge.length - 1)]);
			dog.setBreed(dogBreed[random.nextInt(dogBreed.length - 1)]);

			dogRepo.save(dog);

		}
		List<Truck> trucks = truckRepo.findAll();
		if (trucks.size() < 10) {
			String[] trucksData = dataFileService.getData("src/main/resources/data/trucks.txt");
			Integer[] motorSizes = {10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
			String[] colors = {"Yellow", "Red", "Blue", "Orange", "Purple", "White", "Black", "Grey"};

			for (int i = 0; i < 10; i++) {


				Truck truck = new Truck();
				String modelName = trucksData[random.nextInt(trucksData.length - 1)];
				truck.setModelName(modelName.substring(0, modelName.indexOf(" - ")));
				truck.setColor(colors[random.nextInt(colors.length - 1)]);
				truck.setMotorSize("" + motorSizes[random.nextInt(motorSizes.length - 1)]);

				truckRepo.save(truck);
				
			}
		}
	}

}
