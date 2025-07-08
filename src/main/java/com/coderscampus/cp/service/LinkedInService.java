package com.coderscampus.cp.service;

import com.coderscampus.cp.domain.LinkedIn;
import com.coderscampus.cp.domain.Resume;
import com.coderscampus.cp.domain.Student;
import com.coderscampus.cp.repository.LinkedInRepository;
import com.coderscampus.cp.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.List;
import java.util.Set;

@Service
public class LinkedInService {

    @Autowired
    private LinkedInRepository linkedInRepo;

    @Autowired
    private StudentRepository studentRepo;

    public LinkedIn saveByUid(LinkedIn linkedIn, String uid) {
        if (linkedIn == null || uid == null) {
            return null;
        }

        Student student = studentRepo.findByUid(uid);
        if (student == null) {
            return null;
        }

        if (linkedIn.getStudent() != null) {
            if (!uid.equals(linkedIn.getStudent().getUid())) {
                return null;
            }
        }

        linkedIn.setStudent(student);
        return linkedInRepo.save(linkedIn);
    }

    public List<LinkedIn> findAll() {
        return linkedInRepo.findAll();
    }

    public LinkedIn findById(Long id) {
        if (id == null) {
            return null;
        }
        return linkedInRepo.findById(id).get();
    }

    public void delete(LinkedIn linkedIn) {
        linkedInRepo.delete(linkedIn);
    }

    public boolean isValidURL (String urlString) {
        try {
        URL url = new URL(urlString);
        url.toURI();

        String protocol = url.getProtocol();
        if (!protocol.equals("http") && !protocol.equals("https")) return false;

        String host = url.getHost();
        if (host == null || host.isBlank() || !host.contains(".")) return false;

        String[] hostParts = host.split("\\.");
        String tld = hostParts[hostParts.length - 1].toLowerCase();

        return VALID_TLDS.contains(tld);
        } catch (Exception e) {
            return false;
        }
    }

    private static final Set<String> VALID_TLDS = Set.of(
    "com", "org", "net", "edu", "gov", "io", "dev", "co", "us", "uk", "de", "ca"
);
}

            
            

            

    