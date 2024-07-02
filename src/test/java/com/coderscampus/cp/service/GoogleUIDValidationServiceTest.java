package com.coderscampus.cp.service;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GoogleUIDValidationServiceTest {
	@Autowired
	private GoogleUIDValidationService googleUIDValidationService;

	@Test
	void testIsValidGoogleUID() {
		String uid = "abc";
		assertTrue(googleUIDValidationService.isValidGoogleUID(uid));

	}

}
