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

    private final FoobarRepository foobarRepo;
    private final StudentRepository studentRepo;

    @Autowired
    public FoobarService(FoobarRepository foobarRepo, StudentRepository studentRepo) {
        this.foobarRepo = foobarRepo;
        this.studentRepo = studentRepo;
    }

    public Foobar save(Foobar foobar) {
        System.out.println("FoobarService.java save method| Foobar is: " + foobar);
        return foobarRepo.save(foobar);
    }

    public Foobar saveByUid(Foobar foobar, String uid) {
        Student students = studentRepo.findByUid(uid);
        if (students != null) {
            foobar.setStudent(students);
            foobar.setUid(uid);
        }
        return foobarRepo.save(foobar);
    }

    public List<Foobar> findAll() {
        return foobarRepo.findAll();
    }

    public Foobar findById(Long id) {
        return foobarRepo.findById(id).get();
    }

    public void delete(Foobar foobar) {
        foobarRepo.delete(foobar);
    }

}