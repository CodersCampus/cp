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
        String invalidUid = "abc";
        String passUid = "A1bc-DEF2gh34-IJ567-klmnoPq8";
        
        assertFalse(googleUIDValidationService.isValidGoogleUID(invalidUid));
        assertTrue(googleUIDValidationService.isValidGoogleUID(passUid));
        assertFalse(googleUIDValidationService.isValidGoogleUID(null));
    }

}
