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
        Student students = studentRepo.findByUid(uid);
        if (students != null) {
            checkin.setStudent(students);
            checkin.setUid(uid);
        }
        if (checkin.getDate() == null) {
            if (clientTimeZone == null) {
                // Set the server's default time zone if clientTimeZone is null
                clientTimeZone = ZoneId.systemDefault().getId();
            }
            LocalDateTime clientDateTime = LocalDateTime.now(ZoneId.of(clientTimeZone));
            LocalDateTime serverDateTime = clientDateTime.atZone(ZoneId.of(clientTimeZone))
                    .withZoneSameInstant(ZoneId.systemDefault())
                    .toLocalDateTime();
            checkin.setDate(serverDateTime);
        }
        return checkinRepo.save(checkin);
    }

    public String formatDate(LocalDateTime date) {
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

