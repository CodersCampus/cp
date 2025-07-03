package com.coderscampus.cp.service;

import com.coderscampus.cp.domain.LinkedIn;
import com.coderscampus.cp.domain.Resume;
import com.coderscampus.cp.domain.Student;
import com.coderscampus.cp.repository.LinkedInRepository;
import com.coderscampus.cp.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LinkedInService {

    @Autowired
    private LinkedInRepository linkedInRepo;

    @Autowired
    private StudentRepository studentRepo;

    public LinkedIn save(LinkedIn linkedIn) {
        if (linkedIn == null) {
            throw new IllegalArgumentException("LinkedIn cannot be null");
        }

        if (linkedIn.getStudent() == null) {
            throw new IllegalArgumentException("Student cannot be null");
        }

        // Check if a LinkedIn record already exists for this student
        LinkedIn existingLinkedIn = findById(linkedIn.getStudent().getId());

        if (existingLinkedIn != null) {
            // Update existing record
            updateLinkedInFields(existingLinkedIn, linkedIn);
            return linkedInRepo.save(existingLinkedIn);
        } else {
            // Create a new record
            return linkedInRepo.save(linkedIn);
        }
    }


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
        return linkedInRepo.findById(id).orElse(null);
    }

    public void delete(LinkedIn linkedIn) {
        linkedInRepo.delete(linkedIn);
    }

    private void updateLinkedInFields(LinkedIn existing, LinkedIn updated) {
        if (updated.getBanner() != null) existing.setBanner(updated.getBanner());
        if (updated.getAbout() != null) existing.setAbout(updated.getAbout());
        if (updated.getUrl() != null) existing.setUrl(updated.getUrl());
        if (updated.getFeaturedPosts() != null) existing.setFeaturedPosts(updated.getFeaturedPosts());
        if (updated.getActivity() != null) existing.setActivity(updated.getActivity());
        if (updated.getSkills() != null) existing.setSkills(updated.getSkills());
        if (updated.getEmail() != null) existing.setEmail(updated.getEmail());
        if (updated.getFirstName() != null) existing.setFirstName(updated.getFirstName());
        if (updated.getLastName() != null) existing.setLastName(updated.getLastName());
        if (updated.getBiography() != null) existing.setBiography(updated.getBiography());
        if (updated.getEducation() != null) existing.setEducation(updated.getEducation());
        if (updated.getExperience() != null) existing.setExperience(updated.getExperience());
        if (updated.getLocation() != null) existing.setLocation(updated.getLocation());
        if (updated.getImage() != null) existing.setImage(updated.getImage());
        if (updated.getTitle() != null) existing.setTitle(updated.getTitle());
    }

}