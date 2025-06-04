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

    public List<Networkingresources> findAll() {
        return networkingresourcesRepo.findAll();
    }

    public Networkingresources findById(Long id) {
        if (id == null) {
            return null;
        }
        return networkingresourcesRepo.findById(id).orElseThrow(() -> new RuntimeException("Element not found with ID " + id));
    }

    public void delete(Networkingresources networkingresources) {
        networkingresourcesRepo.delete(networkingresources);
    }

}