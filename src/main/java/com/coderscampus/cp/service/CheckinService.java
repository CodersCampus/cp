package com.coderscampus.cp.service;

import com.coderscampus.cp.domain.Checkin;
import com.coderscampus.cp.domain.Student;
import com.coderscampus.cp.repository.CheckinRepository;
import com.coderscampus.cp.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CheckinService {

    @Autowired
    private CheckinRepository checkinRepo;

    @Autowired
    private StudentRepository studentRepo;

    public Checkin saveByUid(Checkin checkin, String uid, String clientTimeZone) {
        setStudentAndUid(checkin, uid);
        setDateIfNull(checkin, clientTimeZone);
        return checkinRepo.save(checkin);
    }

    private void setStudentAndUid(Checkin checkin, String uid) {
        Student student = studentRepo.findByUid(uid);
        if (student != null) {
            checkin.setStudent(student);
            checkin.setUid(uid);
        }
    }

    private void setDateIfNull(Checkin checkin, String clientTimeZone) {
        if (checkin.getDate() == null) {
            ZoneId clientZoneId = getClientZoneId(clientTimeZone);
            LocalDateTime clientDateTime = LocalDateTime.now(clientZoneId);
            LocalDateTime serverDateTime = convertToServerDateTime(clientDateTime, clientZoneId);
            checkin.setDate(serverDateTime);
        }
    }

    private ZoneId getClientZoneId(String clientTimeZone) {
        return clientTimeZone != null ? ZoneId.of(clientTimeZone) : ZoneId.systemDefault();
    }

    private LocalDateTime convertToServerDateTime(LocalDateTime clientDateTime, ZoneId clientZoneId) {
        return clientDateTime.atZone(clientZoneId)
                .withZoneSameInstant(ZoneId.systemDefault())
                .toLocalDateTime();
    }

    public String getFormattedDate(LocalDateTime date) {
        if (date == null) {
            return "";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yy, h:mm a");
        return date.format(formatter);
    }

    public List<Checkin> findAll() {
        return checkinRepo.findAll().stream().sorted(Comparator.comparing(Checkin::getDate).reversed()).collect(Collectors.toList());
    }

    public Checkin findById(Long id) {
        return checkinRepo.findById(id).get();
    }

    public void delete(Checkin checkin) {
        checkinRepo.delete(checkin);
    }

    public List<Checkin> findByUid(String uid) {
        return checkinRepo.findByUid(uid).stream().sorted(Comparator.comparing(Checkin::getDate).reversed()).collect(Collectors.toList());
    }
}

