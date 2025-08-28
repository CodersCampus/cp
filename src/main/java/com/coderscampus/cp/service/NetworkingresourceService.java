package com.coderscampus.cp.service;

import com.coderscampus.cp.domain.Networkingresource;
import com.coderscampus.cp.domain.Student;
import com.coderscampus.cp.repository.NetworkingresourceRepository;
import com.coderscampus.cp.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NetworkingresourceService {

    @Autowired
    private NetworkingresourceRepository networkingresourcesRepo;

    @Autowired
    private StudentRepository studentRepo;


    public Networkingresource saveByUid(Networkingresource networkingresources, String uid) {
        if (networkingresources == null || uid == null) {
            return null;
        }

        Student student = studentRepo.findByUid(uid);
        if (student == null) {
            return null;
        }

        if (networkingresources.getStudent() != null) {
            if (!uid.equals(networkingresources.getStudent().getUid())) {
                return null;
            }
        }

        networkingresources.setStudent(student);
        return networkingresourcesRepo.save(networkingresources);
    }

    public List<Networkingresource> findAll() {
        return networkingresourcesRepo.findAll();
    }

    public List<Networkingresource> findListByUid(String uid) {
        List<Networkingresource> listForStudent = new ArrayList<>();
        List<Networkingresource> allPersons = networkingresourcesRepo.findAll();
        for (Networkingresource networkingresource : allPersons) {
            if (networkingresource.getStudent().getUid().equals(uid)) {
                listForStudent.add(networkingresource);
            }
        }
        return listForStudent;
    }
    public Networkingresource findById(Long id) {
        if (id == null) {
            return null;
        }
        return networkingresourcesRepo.findById(id).get();
    }

    public void delete(Networkingresource networkingresources) {
        networkingresourcesRepo.delete(networkingresources);
    }

}