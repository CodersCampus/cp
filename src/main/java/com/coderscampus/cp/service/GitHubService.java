package com.coderscampus.cp.service;

import com.coderscampus.cp.domain.GitHub;
import com.coderscampus.cp.domain.Student;
import com.coderscampus.cp.repository.GitHubRepository;
import com.coderscampus.cp.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class GitHubService {

    @Autowired
    private GitHubRepository gitHubRepo;

    @Autowired
    private StudentRepository studentRepo;


    public GitHub saveByUid(GitHub gitHub, String uid) {
        if (gitHub == null || uid == null) {
            return null;
        }

        Student student = studentRepo.findByUid(uid);
        if (student == null) {
            return null;
        }

        if (gitHub.getStudent() != null) {
            if (!uid.equals(gitHub.getStudent().getUid())) {
                return null;
            }
        }

        gitHub.setStudent(student);
        return gitHubRepo.save(gitHub);
    }

    public List<GitHub> findAll() {
        return gitHubRepo.findAll();
    }

    public List<GitHub> findByUid(String uid) {
        return gitHubRepo.findByStudentUid(uid);
    }

    public Optional<GitHub> findOptionalById(Long id) {
        if (id == null) {
            return Optional.empty();
        }
        return gitHubRepo.findById(id);
    }

    public Optional<GitHub> findByIdAndUid(Long id, String uid) {
        if (id == null || uid == null) {
            return Optional.empty();
        }
        return gitHubRepo.findByIdAndStudentUid(id, uid);
    }

    public GitHub findById(Long id) {
        if (id == null) {
            return null;
        }
        return gitHubRepo.findById(id).get();
    }

    public void delete(GitHub gitHub) {
        gitHubRepo.delete(gitHub);
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

    public boolean checkIfExists(String uid) {
        return uid != null && gitHubRepo.existsByStudentUid(uid);
    }
}
