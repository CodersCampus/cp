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

    public GitHub findById(Long id) {
        if (id == null) {
            return null;
        }
        return gitHubRepo.findById(id).get();
    }

    public void delete(GitHub gitHub) {
        gitHubRepo.delete(gitHub);
    }
    
    public void deleteRecordsWithNoStudentAssociated() {
        List<GitHub> allGitHubs = gitHubRepo.findAll();

        for (GitHub gitHub : allGitHubs) {
            if (gitHub.getStudent() == null) {
                gitHubRepo.delete(gitHub);
            }
        }
    }

}