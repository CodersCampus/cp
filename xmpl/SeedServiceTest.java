package com.coderscampus.practice.service;

import java.util.ArrayList;
import java.util.List;

import com.coderscampus.xmpl.SeedService;
import org.junit.jupiter.api.Test;

class SeedServiceTest {

    @Test
    void testRandomTransmission() {


        SeedService seedService = new SeedService();

        List<String> transmissions = new ArrayList<String>();

        for (int i = 0; i < 20; i++) {
            transmissions.add(seedService.randomTransmission());


        }
        assert (transmissions.contains("Automatic"));
        assert (transmissions.contains("Manual"));
    }

}
