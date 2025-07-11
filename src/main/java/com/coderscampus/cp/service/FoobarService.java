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
        if (!isValidInput(foobar, uid)) {
            return null;
        }

        if (!isStudentUidValid(foobar, uid)) {
            return null;
        }

        Student student = studentRepo.findByUid(uid);
        updateFoobarWithStudent(foobar, student, uid);

        return foobarRepo.save(foobar);
    }

    public List<Foobar> findAll() {
        return foobarRepo.findAll();
    }

    public Foobar findById(Long id) {
        if (id == null) {
            return null;
        }
        return foobarRepo.findById(id).orElse(null);
    }

    public void delete(Foobar foobar) {
        foobarRepo.delete(foobar);
    }

    private boolean isValidInput(Foobar foobar, String uid) {
        return foobar != null && uid != null;
    }

    private boolean isStudentUidValid(Foobar foobar, String uid) {
        return foobar.getStudent() == null || uid.equals(foobar.getStudent().getUid());
    }

    private void updateFoobarWithStudent(Foobar foobar, Student student, String uid) {
        if (student != null && foobar.getStudent() != null) {
            foobar.setUid(uid);
        }
        foobar.setStudent(student);
    }
}