package com.coderscampus.cp.service;

import com.coderscampus.cp.domain.Checkin;
import com.coderscampus.cp.domain.Student;
import com.coderscampus.cp.repository.CheckinRepository;
import com.coderscampus.cp.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Method;
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

    @Test
    public void testSetStudentAndUid() throws Exception {
        // Create a sample Checkin object
        Checkin checkin = new Checkin();

        // Create a sample Student object
        Student student = new Student();
        String uid = "sGvKUXo0M4PgKMoPC73fdSrM6659";
        student.setUid(uid);

        // Mock the behavior of the studentRepo.findByUid() method
        when(studentRepo.findByUid(uid)).thenReturn(student);

        // Use reflection to access the private method
        Method setStudentAndUidMethod = CheckinService.class.getDeclaredMethod("setStudentAndUid", Checkin.class, String.class);
        setStudentAndUidMethod.setAccessible(true);

        // Invoke the private method using reflection
        setStudentAndUidMethod.invoke(checkinService, checkin, uid);

        // Verify that the student and UID are set correctly
        assertEquals(student, checkin.getStudent());
        assertEquals(uid, checkin.getUid());
    }

    @Test
    public void testSetDateIfNull() throws Exception {
        // Create a sample Checkin object with null date
        Checkin checkin = new Checkin();

        // Define the client's time zone
        String clientTimeZone = "America/New_York";

        // Use reflection to access the private method
        Method setDateIfNullMethod = CheckinService.class.getDeclaredMethod("setDateIfNull", Checkin.class, String.class);
        setDateIfNullMethod.setAccessible(true);

        // Invoke the private method using reflection
        setDateIfNullMethod.invoke(checkinService, checkin, clientTimeZone);

        // Verify that the date is set to the expected server date and time
        LocalDateTime expectedServerDateTime = LocalDateTime.now(ZoneId.of(clientTimeZone))
                .atZone(ZoneId.of(clientTimeZone))
                .withZoneSameInstant(ZoneId.systemDefault())
                .toLocalDateTime();
        assertEquals(expectedServerDateTime.withNano(0), checkin.getDate().withNano(0));
    }

    @Test
    public void testGetClientZoneId() throws Exception {
        // Define the client's time zone
        String clientTimeZone = "America/New_York";

        // Use reflection to access the private method
        Method getClientZoneIdMethod = CheckinService.class.getDeclaredMethod("getClientZoneId", String.class);
        getClientZoneIdMethod.setAccessible(true);

        // Invoke the private method using reflection
        ZoneId clientZoneId = (ZoneId) getClientZoneIdMethod.invoke(checkinService, clientTimeZone);

        // Verify that the client's time zone ID is returned correctly
        assertEquals(ZoneId.of(clientTimeZone), clientZoneId);
    }

    @Test
    public void testConvertToServerDateTime() throws Exception {
        // Define the client's time zone and date/time
        ZoneId clientZoneId = ZoneId.of("America/New_York");
        LocalDateTime clientDateTime = LocalDateTime.now(clientZoneId);

        // Use reflection to access the private method
        Method convertToServerDateTimeMethod = CheckinService.class.getDeclaredMethod("convertToServerDateTime", LocalDateTime.class, ZoneId.class);
        convertToServerDateTimeMethod.setAccessible(true);

        // Invoke the private method using reflection
        LocalDateTime serverDateTime = (LocalDateTime) convertToServerDateTimeMethod.invoke(checkinService, clientDateTime, clientZoneId);

        // Verify that the server date/time is converted correctly
        LocalDateTime expectedServerDateTime = clientDateTime.atZone(clientZoneId)
                .withZoneSameInstant(ZoneId.systemDefault())
                .toLocalDateTime();
        assertEquals(expectedServerDateTime, serverDateTime);
    }


}