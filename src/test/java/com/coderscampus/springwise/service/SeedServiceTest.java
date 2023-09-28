package com.coderscampus.springwise.service;

import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class SeedServiceTest {

	@Test
	void testRandomTransmission() {

		// list of random transmissions testing for random transmissions
		SeedService seedService = new SeedService();

		List<String> transmissions = new ArrayList<String>();

		for (int i = 0; i < 20; i++) {
			transmissions.add(seedService.randomTransmission());

			System.out.println(transmissions.get(i));
		}

		assert (transmissions.contains("Automatic"));
		assert (transmissions.contains("Manual"));
	}
	@Test
	void testRandomPrice() {
		
		// list of random transmissions testing for random transmissions
		SeedService seedService = new SeedService();

		List<Integer> price = new ArrayList<Integer>();

		for (int i = 0; i < 2000; i++) {
			price.add(seedService.randomPrice());

			System.out.println(price.get(i));
		}

		assert (price.contains(70000));
		assert (price.contains(90000));
		
		
	}
	@Test
	void testRandomYear() {

		// list of random transmissions testing for random transmissions
		SeedService seedService = new SeedService();

		List<Integer> year = new ArrayList<Integer>();

		for (int i = 0; i < 2000; i++) {
			year.add(seedService.randomYear());
			System.out.println(year.get(i));
		}

		assert (year.contains(3000));
		assert (year.contains(1985));

	}
}
