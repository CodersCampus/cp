package com.coderscampus.springwise.service;

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
import com.coderscampus.springwise.domain.Movie;
import com.coderscampus.springwise.domain.Truck;
import com.coderscampus.springwise.repository.AirplaneRepository;
import com.coderscampus.springwise.repository.CarRepository;
import com.coderscampus.springwise.repository.DogRepository;
import com.coderscampus.springwise.repository.FrogRepository;
import com.coderscampus.springwise.repository.MovieRepository;
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
	@Autowired
	private CarRepository carRepo;
	@Autowired
	private MovieRepository movieRepo;
	@Autowired
	private Seed seed;
	
	
	private Random random = new Random();

	private String[] names = null;
	private String[] colors = { "Yellow", "Red", "Blue", "Orange", "Purple", "White", "Black", "Grey" };

	public void populateData() {

		names = dataFileService.getData("src/main/resources/data/names.txt");

		seedDataAirplanes();

		seedDataFrogs();

		seedDataDogs();
		seedDataTrucks();
		seedDataMovies();

		seedDataCars();
	}

	private void seedDataAirplanes() {
		List<Airplane> airplanes = airRepo.findAll();
		if (airplanes.size() < 10) {
			String[] makesModels = dataFileService.getData("src/main/resources/data/airplanes.txt");

			for (int i = 0; i < 10; i++) {

				Airplane airplane = new Airplane();
				String line = makesModels[random.nextInt(makesModels.length - 1)];
				airplane.setModel(line.substring(line.indexOf("|") + 2, line.length() - 1));
				airplane.setMake(line.substring(0, line.indexOf("|")).trim());
				airplane.setColor(colors[random.nextInt(colors.length - 1)]);
				airRepo.save(airplane);
			}
		}
	}

	private void seedDataMovies() {
		List<Movie> movies = movieRepo.findAll();
		if (movies.size() < 10) {
			String[] makesMovies = dataFileService.getData("src/main/resources/data/movies.txt");

			for (int i = 0; i < 10; i++) {
				Movie movie = new Movie();
				String line = makesMovies[random.nextInt(makesMovies.length - 1)];
				int yearOf = Integer.parseInt(line.substring(line.indexOf("|") + 1, line.length()));
				movie.setYearOf(yearOf);
				movie.setName(line.substring(0, line.indexOf("|")).trim());
				String[] movieRatings = { "1", "2", "3", "4", "5" };
				movie.setGenre("unknown");
				movie.setRating(movieRatings[random.nextInt(movieRatings.length - 1)]);
				movieRepo.save(movie);
			}
		}
	}

	private void seedDataFrogs() {
		List<Frog> frogs = frogRepo.findAll();
		if (frogs.size() < 10) {
			String[] frogSpecies = dataFileService.getData("src/main/resources/data/frogs.txt");
			String[] frogAge = { "young", "old" };

			for (int i = 0; i < 10; i++) {

				Frog frog = new Frog();

				frog.setName(names[random.nextInt(names.length - 1)]);
				frog.setAge(frogAge[random.nextInt(frogAge.length - 1)]);
				frog.setSpecies(frogSpecies[random.nextInt(frogSpecies.length - 1)]);

				frogRepo.save(frog);
			}
		}
	}

	private void seedDataDogs() {
		List<Dog> dogs = dogRepo.findAll();
		if (dogs.size() < 10) {
			String[] dogBreed = dataFileService.getData("src/main/resources/data/dogs.txt");
			String[] dogName = names;
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
	
	Integer randomMotorSize() {
		Integer[] motorSizes = { 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20 };
		return motorSizes[random.nextInt(motorSizes.length - 1)];
	}
	
	

	private void seedDataTrucks() {

		List<Truck> trucks = truckRepo.findAll();
		if (trucks.size() < 10) {
			String[] trucksData = dataFileService.getData("src/main/resources/data/trucks.txt");
	
			for (int i = 0; i < 10; i++) {

				Truck truck = new Truck();
				String modelName = trucksData[random.nextInt(trucksData.length - 1)];
				String[] makeAndModel = modelName.split("-");
				truck.setModelName(makeAndModel[0]);
				truck.setManufacturer(makeAndModel[1]);
				truck.setColor(seed.getRandomElement(colors).toString());
//				truck.setMotorSize();

				truckRepo.save(truck);
			}

		}
	}

	String randomTransmission() {
		String[] transmission = { "Automatic", "Manual", "Automatic", "Manual" };
		return transmission[random.nextInt(transmission.length - 1)];
	}

	private void seedDataCars() {

		List<Car> cars = carRepo.findAll();
		if (cars.size() < 30) {
			String[] carsData = dataFileService.getData("src/main/resources/data/cars.txt");
			Integer[] motorSizes = { 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20 };
			Integer[] wheelSizes = { 14, 15, 16, 17, 18, 19, 20, 21 };

			for (int i = 0; i < 10; i++) {

				Car car = new Car();
				String modelName = carsData[random.nextInt(carsData.length - 1)];
				String[] makeAndModel = modelName.split("-");
				car.setModelName(makeAndModel[0]);
				car.setWheelSize(wheelSizes[random.nextInt(wheelSizes.length - 1)]);
				car.setColor(colors[random.nextInt(colors.length - 1)]);
				car.setMotorSize("" + motorSizes[random.nextInt(motorSizes.length - 1)]);
				car.setTransmission(randomTransmission());

				carRepo.save(car);

			}

		}
	}

}
