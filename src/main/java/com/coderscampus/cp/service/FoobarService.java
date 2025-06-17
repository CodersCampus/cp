package com.coderscampus.cp.service;

import com.coderscampus.cp.domain.Foobar;
import com.coderscampus.cp.domain.Student;
import com.coderscampus.cp.repository.FoobarRepository;
import com.coderscampus.cp.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoobarService {

    @Autowired
    private FoobarRepository foobarRepo;

    @Autowired
    private StudentRepository studentRepo;

    public Foobar saveByUid(Foobar foobar, String uid) {
        if (foobar == null || uid == null) {
            return null;
        }

        Student student = studentRepo.findByUid(uid);
        if (student == null) {
            return null;
        }

        if (foobar.getStudent() != null) {
            if (!uid.equals(foobar.getStudent().getUid())) {
                return null;
            }
        }

        foobar.setStudent(student);
        return foobarRepo.save(foobar);
    }

    public List<Foobar> findAll() {
        return foobarRepo.findAll();
    }

    public Foobar findById(Long id) {
        if (id == null) {
            return null;
        }
        return foobarRepo.findById(id).orElseThrow(() -> new RuntimeException("Element not found with ID " + id));
    }

    public void delete(Foobar foobar) {
        foobarRepo.delete(foobar);
    }
}