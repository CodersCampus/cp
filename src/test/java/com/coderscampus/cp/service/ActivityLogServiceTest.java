package com.coderscampus.cp.service;

import com.coderscampus.cp.domain.ActivityLog;
import com.coderscampus.cp.domain.Checkin;

import com.coderscampus.cp.domain.Student;
import com.coderscampus.cp.dto.CheckinDTO;
import com.coderscampus.cp.repository.ActivityLogRepository;
import com.coderscampus.cp.repository.CheckinRepository;
import com.coderscampus.cp.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ActivityLogServiceTest {

	@Autowired
	private StudentRepository studentRepo;

	@Autowired
	private CheckinRepository checkinRepo;
	Student student1;
	Student student2;

	@Autowired
	private ActivityLogRepository activityLogRepository;
	@Autowired
	private ActivityLogService activityLogService;

	String student1Uid;
	String student2Uid;

	List<CheckinDTO> student1CheckinDTOList;
    List<CheckinDTO> student2CheckinDTOList;

	@BeforeEach
	void prepData() {

		student1Uid = UUID.randomUUID().toString();
		student2Uid = UUID.randomUUID().toString();

		student1 = new Student(student1Uid, "name1", 1, "IntelliJ", false, "mentor1", null);
		student2 = new Student(student2Uid, "name2", 2, "IntelliJ", false, "mentor2", null);

		student1 = studentRepo.save(student1);
		student2 = studentRepo.save(student2);

		student1CheckinDTOList = new ArrayList<>();
        student2CheckinDTOList = new ArrayList<>();

		for (int i = 0; i < 4; i++) {
			Checkin checkin = new Checkin();
			checkin.setBlockerDescription("Blocker" + i);
			checkin.setNextAssignment(i);
			checkin.setBlocker(true);
			
			
			checkin.setStudent(student1);
			checkin.setUid(student1Uid);
			checkinRepo.save(checkin);
			ActivityLog activityLog = new ActivityLog();
			
			activityLog.setRole(ActivityLog.Role.OBSERVER);
			activityLog.setCodingType(ActivityLog.CodingType.CRUD);
			activityLog.setIssueNumber(628);
			activityLog.setComment("Update");
			activityLog.setCheckin(checkin);
			activityLogRepository.save(activityLog);
			checkin.getActivityLogs().add(activityLog);
			CheckinDTO checkinDTO = new CheckinDTO(checkin);
			student1CheckinDTOList.add(checkinDTO);
		}
	}

	@AfterEach
	void cleanUpData() {
		student1CheckinDTOList.forEach(checkinDTO -> {
			checkinRepo.findById(checkinDTO.getId()).ifPresent(checkinRepo::delete);
		});

		studentRepo.delete(student1);
		studentRepo.delete(student2);
	}

	@Test
	@Transactional
	void testSaveWhenCheckinIsNull() {
        ActivityLog activityLog = new ActivityLog();
        activityLog.setCheckin(null);
        ActivityLog savedActivityLog = activityLogService.save(activityLog);
        assertNull(savedActivityLog);
	}

	@Test
	@Transactional
	void testSaveWhenCheckinIdIsNull() {
        student1CheckinDTOList.forEach(checkinDTO -> {
            ActivityLog activityLog = new ActivityLog();
            Checkin checkin = checkinRepo.findById(checkinDTO.getId()).get();
            checkin.setId(null);
            activityLog.setCheckin(checkin);
            ActivityLog savedActivityLog = activityLogService.save(activityLog);
            assertNull(savedActivityLog);
        });
	}

	@Test
	@Transactional
	void testSave() {
        AtomicLong i = new AtomicLong(0L);
		student1CheckinDTOList.forEach(checkinDTO -> {
            ActivityLog activityLog = new ActivityLog();
            activityLog.setComment("Test");
			Checkin checkin = checkinRepo.findById(checkinDTO.getId()).get();
			activityLog.setCheckin(checkin);
			ActivityLog savedActivityLog = activityLogService.save(activityLog);
            assertTrue(savedActivityLog.getId() > i.get());
            i.set(savedActivityLog.getId());
            assertEquals(activityLog.getComment(), savedActivityLog.getComment());
            assertEquals(activityLog.getCheckin().getId(), savedActivityLog.getCheckin().getId());
		});
	}

	@Test
	@Transactional
	void testFindByCheckinWhenCheckinIdIsNull() {
		 student1CheckinDTOList.forEach(checkinDTO -> {
	            Checkin checkin = checkinRepo.findById(checkinDTO.getId()).get();
	            checkin.setId(null);
	            assertTrue(activityLogService.findByCheckin(checkin.getId()).isEmpty());
	        });
	}

	@Test
	@Transactional
	void testFindByCheckin() {
                student1CheckinDTOList.forEach(checkinDTO -> {
                    List<ActivityLog> activityLogList = activityLogService.findByCheckin(checkinDTO.getId());
                    assertEquals(1, activityLogList.size());
                    ActivityLog activityLog = new ActivityLog();
                    
                    activityLog.setRole(ActivityLog.Role.OBSERVER);
                    activityLog.setCodingType(ActivityLog.CodingType.CRUD);
                    activityLog.setIssueNumber(628);
                    activityLog.setComment("Update");
                    Checkin checkin = activityLogList.get(0).getCheckin();
                    activityLog.setCheckin(checkin);
                    activityLogRepository.save(activityLog);
                    activityLogList.add(activityLog);
                    assertEquals(2, activityLogList.size());
                });
    }

	@Test
	@Transactional
	void testFindByActivityLogIdWhenActivityLogIdIsNull() {
        student1CheckinDTOList.forEach(checkinDTO -> {
            List<ActivityLog> activityLogList = activityLogService.findByCheckin(checkinDTO.getId());
            Checkin checkin = activityLogList.get(0).getCheckin();
            ActivityLog activityLog = checkin.getActivityLogs().get(0);
            assertNotNull(activityLog);
            activityLog.setId(null);
            assertNull(activityLogService.findById(activityLog.getId()));
        });
	}

	@Test
	@Transactional
	void testFindByActivityLogId() {
		student1CheckinDTOList.forEach(checkinDTO -> {
            List<ActivityLog> activityLogList = activityLogService.findByCheckin(checkinDTO.getId());
            Checkin checkin = activityLogList.get(0).getCheckin();
            ActivityLog activityLog = checkin.getActivityLogs().get(0);
            assertEquals(activityLog, activityLogService.findById(activityLog.getId()));
        });
	}
	@Test
	@Transactional
	void testFindByCheckinWhenCheckinIsNull() {
		List<ActivityLog> activityLogList = activityLogService.findByCheckin(null);
		assertTrue(activityLogList.isEmpty());
	}

    @Test
    @Transactional
    void testGetNumberOfIssuesContributedToWhenMultipleStudentsExist() {

        for (int i = 0; i < 4; i++) {
			Checkin checkin = new Checkin();
			checkin.setBlockerDescription("Blocker" + i);
			checkin.setNextAssignment(i);
			checkin.setBlocker(true);


			checkin.setStudent(student2);
			checkin.setUid(student2Uid);
			checkinRepo.save(checkin);
			ActivityLog activityLog = new ActivityLog();

			activityLog.setRole(ActivityLog.Role.OBSERVER);
			activityLog.setCodingType(ActivityLog.CodingType.CRUD);
			//student 2 will have 4 issue numbers, where student 1 has 1
            activityLog.setIssueNumber(628 + i);
			activityLog.setComment("Update");
			activityLog.setCheckin(checkin);
			activityLogRepository.save(activityLog);
			checkin.getActivityLogs().add(activityLog);
			CheckinDTO checkinDTO = new CheckinDTO(checkin);
			student2CheckinDTOList.add(checkinDTO);
		}

        //Integer student1NumberOfIssues = student1CheckinDTOList.size();
        Integer student1NumberOfIssues = activityLogService.getNumberOfIssues(student1Uid);
        Integer student2NumberOfIssues = activityLogService.getNumberOfIssues(student2Uid);

        assertEquals(1, student1NumberOfIssues);
        assertEquals(4, student2NumberOfIssues);
    }

    @Test
    @Transactional
    void testGetNumberForEachRoleWhenMultipleStudentsExist() {

        for (int i = 0; i < 4; i++) {
			Checkin checkin = new Checkin();
			checkin.setBlockerDescription("Blocker" + i);
			checkin.setNextAssignment(i);
			checkin.setBlocker(true);


			checkin.setStudent(student2);
			checkin.setUid(student2Uid);
			checkinRepo.save(checkin);
			ActivityLog activityLog = new ActivityLog();

			activityLog.setRole(ActivityLog.Role.OBSERVER);
			activityLog.setCodingType(ActivityLog.CodingType.CRUD);
            activityLog.setIssueNumber(629 + i);
			activityLog.setComment("Update");
			activityLog.setCheckin(checkin);
			activityLogRepository.save(activityLog);
			checkin.getActivityLogs().add(activityLog);
			CheckinDTO checkinDTO = new CheckinDTO(checkin);
			student2CheckinDTOList.add(checkinDTO);
		}

        Integer student1NumberOfObservations = activityLogService.getRoleStatsForActivity("OBSERVER", student1Uid);
        Integer student2NumberOfObservations = activityLogService.getRoleStatsForActivity("OBSERVER", student2Uid);

        assertEquals(4, student1NumberOfObservations);
        assertEquals(4, student2NumberOfObservations);
    }

    @Test
    @Transactional
    void testGetNumberForEachTypeWhenMultipleStudentsExist() {

        for (int i = 0; i < 3; i++) {
			Checkin checkin = new Checkin();
			checkin.setBlockerDescription("Blocker" + i);
			checkin.setNextAssignment(i);
			checkin.setBlocker(true);


			checkin.setStudent(student2);
			checkin.setUid(student2Uid);
			checkinRepo.save(checkin);
			ActivityLog activityLog = new ActivityLog();


			activityLog.setRole(ActivityLog.Role.OBSERVER);
			activityLog.setCodingType(ActivityLog.CodingType.CRUD);
            activityLog.setIssueNumber(629 + i);
			activityLog.setComment("Update");
			activityLog.setCheckin(checkin);
			activityLogRepository.save(activityLog);
			checkin.getActivityLogs().add(activityLog);
			CheckinDTO checkinDTO = new CheckinDTO(checkin);
			student2CheckinDTOList.add(checkinDTO);
		}

        Integer student1NumberOfCrudOperations = activityLogService.getCodingTypeStatsForActivity("CRUD", student1Uid);
        Integer student2NumberOfCrudOperations = activityLogService.getCodingTypeStatsForActivity("CRUD", student2Uid);

        assertEquals(4, student1NumberOfCrudOperations);
        assertEquals(3, student2NumberOfCrudOperations);
    }

}
