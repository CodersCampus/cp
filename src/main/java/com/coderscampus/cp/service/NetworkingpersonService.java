package com.coderscampus.cp.service;

import com.coderscampus.cp.domain.Networkingperson;
import com.coderscampus.cp.domain.Student;
import com.coderscampus.cp.repository.NetworkingpersonRepository;
import com.coderscampus.cp.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public void delete(Networkingperson networkingperson) {
        networkingpersonRepo.delete(networkingperson);
    }

}