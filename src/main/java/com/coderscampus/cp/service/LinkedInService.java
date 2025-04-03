package com.coderscampus.cp.service;

import com.coderscampus.cp.domain.LinkedIn;
import com.coderscampus.cp.domain.Resume;
import com.coderscampus.cp.domain.Student;
import com.coderscampus.cp.repository.LinkedInRepository;
import com.coderscampus.cp.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LinkedInService {

    @Autowired
    private LinkedInRepository linkedInRepo;

    @Autowired
    private StudentRepository studentRepo;

    public LinkedIn save(LinkedIn linkedIn) {
        return linkedInRepo.save(linkedIn);
    }


    public LinkedIn saveByUid(LinkedIn linkedIn, String uid) {
        Student students = studentRepo.findByUid(uid);
        if (students != null) {
            linkedIn.setStudent(students);
        }
        return linkedInRepo.save(linkedIn);
    }

    public List<LinkedIn> findAll() {
        return linkedInRepo.findAll();
    }

    public LinkedIn findById(Long id) {
        return linkedInRepo.findById(id).get();
    }

    public void delete(LinkedIn linkedIn) {
        linkedInRepo.delete(linkedIn);
    }

}