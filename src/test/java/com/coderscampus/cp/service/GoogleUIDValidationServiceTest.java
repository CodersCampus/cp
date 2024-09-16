package com.coderscampus.cp.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class GoogleUIDValidationServiceTest {
    @Autowired
    private GoogleUIDValidationService googleUIDValidationService;

    @Test
    void testIsValidGoogleUID() {
        String invalidUid = "abc";
        String passUid = "A1bc-DEF2gh34-IJ567-klmnoPq8";

        assertFalse(googleUIDValidationService.isValidGoogleUID(invalidUid));
        assertTrue(googleUIDValidationService.isValidGoogleUID(passUid));
        assertFalse(googleUIDValidationService.isValidGoogleUID(null));
    }

}
