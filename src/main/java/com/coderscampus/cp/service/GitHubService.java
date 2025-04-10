package com.coderscampus.cp.service;

import com.coderscampus.cp.domain.;
import com.coderscampus.cp.domain.Student;
import com.coderscampus.cp.repository.Repository;
import com.coderscampus.cp.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GitHubService {

    @Autowired
    private Repository resumeRepo;

    @Autowired
    private StudentRepository studentRepo;

    public  save( resume) {
        return resumeRepo.save(resume);
    }

    public  saveByUid( resume, String uid) {
        Student students = studentRepo.findByUid(uid);
        if (students != null) {
            resume.setStudent(students);
        }
        return resumeRepo.save(resume);
    }

    public List<> findAll() {
        return resumeRepo.findAll();
    }

    public  findById(Long id) {
        return resumeRepo.findById(id).get();
    }

    public void delete( resume) {
        resumeRepo.delete(resume);
    }

}