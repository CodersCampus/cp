package com.coderscampus.springwise.service;

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
	void testRandomMotorSize() {
		SeedService seedService = new SeedService();

		List<Integer> motorSizes = new ArrayList<>();
		
		for (int i = 0; i < 20; i++) {
			motorSizes.add(seedService.randomMotorSize());

			System.out.println(motorSizes.get(i));
		}

		assert (motorSizes.contains(10));
		assert (motorSizes.contains(11));
	}
	

}
