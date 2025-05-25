package com.coderscampus.cp.service;

import com.coderscampus.cp.domain.GitHub;
import com.coderscampus.cp.domain.Student;
import com.coderscampus.cp.repository.GitHubRepository;
import com.coderscampus.cp.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GitHubService {

    @Autowired
    private GitHubRepository gitHubRepo;

    @Autowired
    private StudentRepository studentRepo;

    public GitHub save(GitHub gitHub) {
        return gitHubRepo.save(gitHub);
    }

    public GitHub saveByUid(GitHub gitHub, String uid) {
        Student student = studentRepo.findByUid(uid);
        if (gitHub != null && student != null && gitHub.getStudent().getUid().equals(uid)) {
            gitHub.setStudent(student);
        } else {
            return null;
        }
        return gitHubRepo.save(gitHub);
    }

    public List<GitHub> findAll() {
        return gitHubRepo.findAll();
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

}