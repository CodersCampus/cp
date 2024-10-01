package com.coderscampus.cp.service;

import com.coderscampus.cp.domain.ActivityLog;
import com.coderscampus.cp.domain.Checkin;
import com.coderscampus.cp.domain.Checkin.CodingType;
import com.coderscampus.cp.domain.Checkin.Role;
import com.coderscampus.cp.domain.Student;
import com.coderscampus.cp.dto.CheckinDTO;
import com.coderscampus.cp.dto.StudentDTO;
import com.coderscampus.cp.repository.ActivityLogRepository;
import com.coderscampus.cp.repository.CheckinRepository;
import com.coderscampus.cp.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ActivityLogServiceTest {

	@Autowired
	private StudentRepository studentRepo;

	@Autowired
	private CheckinService checkinService;
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
			checkin.setBlockers(true);
			checkin.setRole(Checkin.Role.CODER);
			checkin.setCodingType(Checkin.CodingType.CRUD);
			checkin.setStudent(student1);
			checkin.setUid(student1Uid);
			checkinRepo.save(checkin);
			ActivityLog activityLog = new ActivityLog();
			activityLog.setIsSetUp(true);
			activityLog.setAvailable(true);
			activityLog.setRole(Checkin.Role.OBSERVER);
			activityLog.setCodingType(Checkin.CodingType.CRUD);
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
		student2CheckinDTOList.forEach(checkinDTO -> {
			checkinRepo.findById(checkinDTO.getId()).ifPresent(checkinRepo::delete);
		});
		studentRepo.delete(student1);
		studentRepo.delete(student2);
	}

	@Test
	@Transactional
	void testSaveWhenCheckinIsNull() {

	}

	@Test
	@Transactional
	void testSaveWhenCheckinIdIsNull() {

	}

	@Test
	@Transactional
	void testSave() {
		ActivityLog activityLog = new ActivityLog();
		activityLog.setAvailable(true);
		activityLog.setCodingType(CodingType.CRUD);
		activityLog.setComment("Test");
		activityLog.setIsSetUp(true);
		activityLog.setIssueNumber(15);
		activityLog.setRole(Role.GUIDE);
		activityLog.setSetUp(true);

		student1CheckinDTOList.forEach(checkinDTO -> {
			Checkin checkin = checkinRepo.findById(checkinDTO.getId()).get();
			activityLog.setCheckin(checkin);
			activityLogService.save(activityLog);
		});
		//start here next time
	}

	@Test
	@Transactional
	void testFindByCheckinWhenCheckinIsNull() {

	}

	@Test
	@Transactional
	void testFindByCheckinWhenCheckinIdIsNull() {

	}

	@Test
	@Transactional
	void testFindByCheckin() {

	}

	@Test
	@Transactional
	void testFindByAcvtivityLogIdWhenActivityLogIsNull() {

	}

	@Test
	@Transactional
	void testFindByActivityLogIdWhenActivityLogIdIsNull() {

	}

	@Test
	@Transactional
	void testFindByActivityLogId() {

	}
}
