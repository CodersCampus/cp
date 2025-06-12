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


    public LinkedIn saveByUid(LinkedIn linkedIn, String uid) {
        if (linkedIn == null || uid == null) {
            return null;
        }

        Student student = studentRepo.findByUid(uid);
        if (student == null) {
            return null;
        }

        if (linkedIn.getStudent() != null) {
            if (!uid.equals(linkedIn.getStudent().getUid())) {
                return null;
            }
        }

        linkedIn.setStudent(student);
        return linkedInRepo.save(linkedIn);
    }

    public List<LinkedIn> findAll() {
        return linkedInRepo.findAll();
    }

    public LinkedIn findById(Long id) {
        if (id == null) {
            return null;
        }
        return linkedInRepo.findById(id).get();
    }

    public void delete(LinkedIn linkedIn) {
        linkedInRepo.delete(linkedIn);
    }

    public void deleteRecordsWithNoStudentAssociated() {
        List<LinkedIn> allLinkedIns = linkedInRepo.findAll();

        for (LinkedIn linkedIn : allLinkedIns) {
            if (linkedIn.getStudent() == null) {
                linkedInRepo.delete(linkedIn);
            }
        }
    }

}