package com.coderscampus.springwise.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.Test;

//import com.coderscampus.springwise.domain.TransmissionType;

class SeedServiceTest {

//	@Test
//	void testRandomTransmission() {
//
//		// list of random transmissions testing for random transmissions
//		SeedService seedService = new SeedService();
//
//		TransmissionType[] transmissions = TransmissionType.values();
//		
//		List<TransmissionType> seedTransmissions = new ArrayList<>();
//
//		for (int i = 0; i < 20; i++) {
//			Random random = new Random();
//			int randomIndex = random.nextInt(transmissions.length);
//			TransmissionType transmission = transmissions[randomIndex];
//			seedTransmissions.add(transmission);
//
//			System.out.println(transmission);
//		}
//
//		assert (seedTransmissions.contains(TransmissionType.AUTOMATIC));
//		assert (seedTransmissions.contains(TransmissionType.MANUAL));
//	}
	
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
