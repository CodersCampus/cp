package com.coderscampus.cp.service;

import com.coderscampus.cp.domain.Profile;
import com.coderscampus.cp.repository.ProfileRepository;
import com.coderscampus.cp.repository.StudentRepository;
import com.coderscampus.cp.repository.UserRepository;

public class ProfileService {
    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;
    private final StudentRepository studentRepository;

    public ProfileService(ProfileRepository profileRepository, UserRepository userRepository, StudentRepository studentRepository) {
        this.profileRepository = profileRepository;
        this.userRepository = userRepository;
        this.studentRepository = studentRepository;
    }

    public Profile create(Profile profile) {
        if (profile == null) {
            throw new IllegalArgumentException("Profile cannot be null");
        }
        return profileRepository.save(profile);
    }

    public Profile read(String uid) {
        if (uid == null || uid.isEmpty()) {
            throw new IllegalArgumentException("UID cannot be null or empty");
        }

        // Find the profile ID associated with the student using the UID
        Long profileId = studentRepository.findProfileIdByUid(uid)
                .orElseThrow(() -> new RuntimeException("Profile not found for UID: " + uid));

        return profileRepository.findById(profileId)
                .orElseThrow(() -> new RuntimeException("Profile not found with id: " + profileId));
    }

    public Profile update(Long id, Profile profile) {
        if (profile == null) {
            throw new IllegalArgumentException("Profile cannot be null");
        }
        if (!profileRepository.existsById(id)) {
            throw new RuntimeException("Profile not found with id: " + id);
        }
        profile.setId(id);
        return profileRepository.save(profile);
    }

    public void delete(Long id) {
        if (!profileRepository.existsById(id)) {
            throw new RuntimeException("Profile not found with id: " + id);
        }
        profileRepository.deleteById(id);
    }

}
