package com.coderscampus.springwise.service;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.jupiter.api.Test;

class SeedTest {

	@Test
	void testGetRandomElement() {
		Seed seed = new Seed();
		
		String[] colors = {"blue", "green", "red", "yellow", "purple", "brown"};
		String[] numbers = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
		String[] transmissions = {"Automatic", "Manual"};
		String[] ideas = {"None"};
		
		Collection<String> colorList= new ArrayList<>();
		Collection<String> numberList= new ArrayList<>();
		Collection<String> transmissionList= new ArrayList<>();
		Collection<String> ideaList= new ArrayList<>();

		for (int i = 0; i < 100; i++) {
			colorList.add(seed.getRandomElement(colors).toString());
			numberList.add(seed.getRandomElement(numbers).toString());
			transmissionList.add(seed.getRandomElement(transmissions).toString());
			ideaList.add(seed.getRandomElement(ideas).toString());
		}
		
		assert(colorList.contains("blue"));
		assert(colorList.contains("brown"));
		
		assert(numberList.contains("1"));
		assert(numberList.contains("10"));
		
		assert(transmissionList.contains("Automatic"));
		
		assert(ideaList.contains("None"));
	}

}
