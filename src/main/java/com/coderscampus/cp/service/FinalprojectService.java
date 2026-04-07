package com.coderscampus.cp.service;

import com.coderscampus.cp.domain.Finalproject;
import com.coderscampus.cp.domain.Student;
import com.coderscampus.cp.repository.FinalprojectRepository;
import com.coderscampus.cp.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.List;

@Service
public class FinalprojectService {

    @Autowired
    private FinalprojectRepository finalprojectRepo;

    @Autowired
    private StudentRepository studentRepo;


    public Finalproject saveByUid(Finalproject finalproject, String uid) {
        if (finalproject == null || uid == null) {
            return null;
        }

        Student student = studentRepo.findByUid(uid);
        if (student == null) {
            return null;
        }

        if (finalproject.getStudent() != null) {
            if (!uid.equals(finalproject.getStudent().getUid())) {
                return null;
            }
        }

        finalproject.setStudent(student);
        return finalprojectRepo.save(finalproject);
    }

    public List<Finalproject> findAll() {
        return finalprojectRepo.findAllWithStudents();
    }

    public Finalproject findById(Long id) {
        if (id == null) {
            return null;
        }
        return finalprojectRepo.findById(id).get();
    }

    public void delete(Finalproject finalproject) {
        finalprojectRepo.delete(finalproject);
    }

    public boolean isValidURL (String urlString) {
        try {
        URL url = new URL(urlString);
        url.toURI();

        String protocol = url.getProtocol();
        if (!protocol.equals("http") && !protocol.equals("https")) return false;

        String host = url.getHost();
        return host != null && !host.isBlank() && !"localhost".equalsIgnoreCase(host);
        } catch (Exception e) {
            return false;
        }
    }
}
