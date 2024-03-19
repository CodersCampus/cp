package com.coderscampus.cp.service;

import com.coderscampus.cp.domain.Checkin;
import com.coderscampus.cp.domain.Student;
import com.coderscampus.cp.repository.CheckinRepository;
import com.coderscampus.cp.repository.StudentRepository;
import com.coderscampus.cp.service.CheckinService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CheckinServiceDateTimeTest {
    @Mock
    private CheckinRepository checkinRepo;

    @Mock
    private StudentRepository studentRepo;

    @InjectMocks
    private CheckinService checkinService;

    @Test
    public void testSaveCheckinWithStudentTimeZone() {
        // Create a sample Checkin object
        Checkin checkin = new Checkin();
        // Set other fields of the checkin object as needed

        // Create a sample Student object
        Student student = new Student();
        student.setUid("sGvKUXo0M4PgKMoPC73fdSrM6659");
        // Set other fields of the student object as needed

        // Define the client's time zone
        String clientTimeZone = "America/New_York";
        LocalDateTime clientDateTime = LocalDateTime.now(ZoneId.of(clientTimeZone));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy h:mma");
        String formattedClientDateTime = clientDateTime.format(formatter);
        System.out.println("clientDateTime (" + clientTimeZone + "): " + formattedClientDateTime);

        // Define the expected server date and time
        LocalDateTime expectedServerDateTime = clientDateTime.atZone(ZoneId.of(clientTimeZone))
                .withZoneSameInstant(ZoneId.systemDefault())
                .toLocalDateTime();
        String formattedExpectedServerDateTime = expectedServerDateTime.format(formatter);
        System.out.println("expectedServerDateTime: " + formattedExpectedServerDateTime);

        // Mock the behavior of the studentRepo.findByUid() method
        when(studentRepo.findByUid(student.getUid())).thenReturn(student);

        // Mock the behavior of the checkinRepo.save() method
        when(checkinRepo.save(checkin)).thenReturn(checkin);

        // Call the saveByUid method with the client's time zone
        Checkin savedCheckin = checkinService.saveByUid(checkin, student.getUid(), clientTimeZone);
        System.out.println("savedCheckin: " + savedCheckin);

        // Verify that the checkin's date is set to the expected server date and time
        assertEquals(expectedServerDateTime.withNano(0), savedCheckin.getDate().withNano(0));
    }
}