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

	private Random random = new Random();

	public void populateData() {

		seedDataAirplanes();

		seedDataFrogs();

		seedDataDogs();
		seedDataTrucks();
	}

	private void seedDataAirplanes() {
		Airplane airplane = new Airplane();
		airplane.setModel("747");
		airplane.setMake("Boeing");
		airplane.setColor("Fusia");
		airRepo.save(airplane);
	}

	private void seedDataFrogs() {
		String[] frogName = { "kermit", "frogger", "mr. toad", "tree frog" };
		String[] frogSpecies = { "blue", "green", "pink", "purple" };
		String[] frogAge = { "young", "old", "young", "old" };

		for (int i = 0; i < 10; i++) {

			Frog frog = new Frog();

			frog.setName(frogName[random.nextInt(frogName.length - 1)]);
			frog.setAge(frogAge[random.nextInt(frogAge.length - 1)]);
			frog.setSpecies(frogSpecies[random.nextInt(frogSpecies.length - 1)]);

			frogRepo.save(frog);

		}
	}

	private void seedDataDogs() {
		List<Dog> dogs = dogRepo.findAll();
		if (dogs.size() < 10) {
			String[] dogBreed = dataFileService.getData("src/main/resources/data/dogs.txt");
			String[] dogName = dataFileService.getData("src/main/resources/data/dognames.txt");
			String[] dogAge = { "young", "old", "young", "old" };

			for (int i = 0; i < 10; i++) {

				Dog dog = new Dog();

				dog.setName(dogName[random.nextInt(dogName.length - 1)]);
				dog.setAge(dogAge[random.nextInt(dogAge.length - 1)]);
				dog.setBreed(dogBreed[random.nextInt(dogBreed.length - 1)]);

				dogRepo.save(dog);

			}
		}
	}

	private void seedDataTrucks() {

		List<Truck> trucks = truckRepo.findAll();
		if (trucks.size() < 10) {
			String[] trucksData = dataFileService.getData("src/main/resources/data/trucks.txt");
			Integer[] motorSizes = { 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20 };
			String[] colors = { "Yellow", "Red", "Blue", "Orange", "Purple", "White", "Black", "Grey" };

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
