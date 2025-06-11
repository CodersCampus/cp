package com.coderscampus.cp.service;

import com.coderscampus.cp.domain.Resume;
import com.coderscampus.cp.domain.Student;
import com.coderscampus.cp.repository.ResumeRepository;
import com.coderscampus.cp.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResumeService {

    @Autowired
    private ResumeRepository resumeRepo;

    @Autowired
    private StudentRepository studentRepo;


    public Resume saveByUid(Resume resume, String uid) {
        if (resume == null || uid == null) {
            return null;
        }

        Student student = studentRepo.findByUid(uid);
        if (student == null) {
            return null;
        }

        if (resume.getStudent() != null) {
            if (!uid.equals(resume.getStudent().getUid())) {
                return null;
            }
        }

        resume.setStudent(student);
        return resumeRepo.save(resume);
    }

    public List<Resume> findAll() {
        return resumeRepo.findAll();
    }

    public Resume findById(Long id) {
        if (id == null) {
            return null;
        }
        return resumeRepo.findById(id).get();
    }

    public void delete(Resume resume) {
        resumeRepo.delete(resume);
    }

}