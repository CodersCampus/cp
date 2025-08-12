package com.coderscampus.cp.manual;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class TestDataLoader implements CommandLineRunner {

    private final TestDataService testDataService;

    public TestDataLoader(TestDataService testDataService) {
        this.testDataService = testDataService;
    }

    @Override
    public void run(String... args) throws Exception {

        testDataService.prepData();
//        // Example: Clear existing test data
//        musicDocumentRepository.deleteAll();
//        userRepository.deleteAll();
//
//        // Insert test data
//        User user = new User();
//        user.setName("Test User");
//        user.setEmail("test@example.com");
//        userRepository.save(user);
//
//        MusicDocument doc = new MusicDocument();
//        doc.setTitle("Sample Score");
//        doc.setUser(user);
//        musicDocumentRepository.save(doc);

        System.out.println("✅ Test data created");

        // Exit after seeding
        //System.exit(0);
    }
}