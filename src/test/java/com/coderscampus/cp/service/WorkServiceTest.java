package com.coderscampus.cp.service;

import com.coderscampus.cp.domain.Work;
import com.coderscampus.cp.repository.WorkRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class WorkServiceTest {
    @Autowired
    private WorkService workService;

    @Autowired
    private WorkRepository workRepo;

    private Work testWork;
    List<Work> works;

    @BeforeEach
    void setup() {
        works = new ArrayList<>();
        for (int i = 0; i < 21; i++) {
            Work work = new Work();
            work.setStudentName("Student" + i);
            work.setDateBackDoorForTesting(getInstant(i));
            work.setAssignmentNumber(i);
            work.setNumberMinutes(100 + i);
            work.setDescription("Description" + i);
            workRepo.save(work);
            works.add(work);
        }
    }

    private static Instant getInstant(int days) {
        return Instant.now().minus(days, ChronoUnit.DAYS);
    }

    @AfterEach
    void cleanup() {
        works.forEach(work -> workRepo.delete(work));
    }

    @Test
    @Transactional
    void testSaveWork() {
        for (int i = 0; i < 4; i++) {
            Work work = works.get(i);
            assertNotNull(work);
            assertNotNull(work.getId());
            assertEquals(100 + i, work.getNumberMinutes());
            assertEquals("Description" + i, work.getDescription());
            assertEquals("Student" + i, work.getStudentName());
            assertEquals(i, work.getAssignmentNumber());
            System.out.println(work.getId());

        }

    }
}
