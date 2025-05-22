package com.coderscampus.cp.service;

import com.coderscampus.cp.domain.Networkingresources;
import com.coderscampus.cp.domain.Student;
import com.coderscampus.cp.repository.NetworkingresourcesRepository;
import com.coderscampus.cp.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NetworkingresourcesService {

    @Autowired
    private NetworkingresourcesRepository networkingresourcesRepo;

    @Autowired
    private StudentRepository studentRepo;

    public Networkingresources save(Networkingresources networkingresources) {
        return networkingresourcesRepo.save(networkingresources);
    }

    public Networkingresources saveByUid(Networkingresources networkingresources, String uid) {
        Student students = studentRepo.findByUid(uid);
        if (students != null) {
            networkingresources.setStudent(students);
        }
        return networkingresourcesRepo.save(networkingresources);
    }

    public List<Networkingresources> findAll() {
        return networkingresourcesRepo.findAll();
    }

    public Networkingresources findById(Long id) {
        return networkingresourcesRepo.findById(id).orElseThrow(() -> new RuntimeException("Element not found with ID " + id));
    }

    public void delete(Networkingresources networkingresources) {
        networkingresourcesRepo.delete(networkingresources);
    }

}