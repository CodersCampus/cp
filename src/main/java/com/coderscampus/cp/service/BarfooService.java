package com.coderscampus.cp.service;

import com.coderscampus.cp.domain.Barfoo;
import com.coderscampus.cp.domain.Student;
import com.coderscampus.cp.repository.BarfooRepository;
import com.coderscampus.cp.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BarfooService {

    @Autowired
    private BarfooRepository barfooRepo;

    @Autowired
    private StudentRepository studentRepo;

    public Barfoo save(Barfoo barfoo) {
        return barfooRepo.save(barfoo);
    }

    public Barfoo saveByUid(Barfoo barfoo, String uid) {
        Student students = studentRepo.findByUid(uid);
        if (students != null) {
            barfoo.setStudent(students);
            barfoo.setUid(uid);
        }
        return barfooRepo.save(barfoo);
    }

    public List<Barfoo> findAll() {
        return barfooRepo.findAll();
    }

    public Barfoo findById(Long id) {
        return barfooRepo.findById(id).orElseThrow(() -> new RuntimeException("Element not found with ID " + id));
    }

    public void delete(Barfoo barfoo) {
        barfooRepo.delete(barfoo);
    }

}