package com.coderscampus.cp.service;

import com.coderscampus.cp.domain.Checkin;
import com.coderscampus.cp.domain.Student;
import com.coderscampus.cp.repository.CheckinRepository;
import com.coderscampus.cp.repository.StudentRepository;
import net.minidev.json.JSONUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MockCheckinServiceTest {
    @Mock
    private CheckinRepository checkinRepo;

    @Mock
    private StudentRepository studentRepo;

    @InjectMocks
    private CheckinService checkinService;



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


}