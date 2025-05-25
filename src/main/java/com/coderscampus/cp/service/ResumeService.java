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

    public Resume save(Resume resume) {
        return resumeRepo.save(resume);
    }

    public Resume saveByUid(Resume resume, String uid) {
        Student student = studentRepo.findByUid(uid);

        if (resume != null && student != null && resume.getStudent().getUid().equals(uid)) {
            resume.setStudent(student);
        } else {
            return null;
        }
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