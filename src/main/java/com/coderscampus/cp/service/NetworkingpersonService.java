package com.coderscampus.cp.service;

import com.coderscampus.cp.domain.Networkingperson;
import com.coderscampus.cp.domain.Student;
import com.coderscampus.cp.repository.NetworkingpersonRepository;
import com.coderscampus.cp.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class NetworkingpersonService {

    @Autowired
    private NetworkingpersonRepository networkingpersonRepo;

    @Autowired
    private StudentRepository studentRepo;

    public Networkingperson saveByUid(Networkingperson networkingperson, String uid) {
        if (networkingperson == null || uid == null) {
            return null;
        }

        Student student = studentRepo.findByUid(uid);
        if (student == null) {
            return null;
        }

        if (networkingperson.getStudent() != null) {
            if (!uid.equals(networkingperson.getStudent().getUid())) {
                return null;
            }
        }

        networkingperson.setStudent(student);
        return networkingpersonRepo.save(networkingperson);
    }

    public List<Networkingperson> findAll() {
        return networkingpersonRepo.findAll();
    }

    public List<Networkingperson> findByUid(String uid) {
        return networkingpersonRepo.findByStudentUid(uid);
    }

    public List<Networkingperson> findListByUid(String uid) {
        List<Networkingperson> listForStudent = new ArrayList<>();
        List<Networkingperson> allPersons = networkingpersonRepo.findAll();
        for (Networkingperson networkingperson : allPersons) {
            if (networkingperson.getStudent().getUid().equals(uid)) {
                listForStudent.add(networkingperson);
            }
        }
        return listForStudent;
    }

    public Networkingperson findById(Long id) {
        if (id == null) {
            return null;
        }
        return networkingpersonRepo.findById(id).get();
    }

    public Optional<Networkingperson> findOptionalById(Long id) {
        if (id == null) {
            return Optional.empty();
        }
        return networkingpersonRepo.findById(id);
    }

    public Optional<Networkingperson> findByIdAndUid(Long id, String uid) {
        if (id == null || uid == null) {
            return Optional.empty();
        }
        return networkingpersonRepo.findByIdAndStudentUid(id, uid);
    }

    public void delete(Networkingperson networkingperson) {
        networkingpersonRepo.delete(networkingperson);
    }

}
