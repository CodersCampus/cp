package com.coderscampus.cp.service;

import com.coderscampus.cp.domain.Profile;
import com.coderscampus.cp.repository.ProfileRepository;
import com.coderscampus.cp.repository.StudentRepository;
import com.coderscampus.cp.repository.UserRepository;

public class ProfileService {
    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;

    public ProfileService(ProfileRepository profileRepository, UserRepository userRepository, StudentRepository studentRepository) {
        this.profileRepository = profileRepository;
        this.userRepository = userRepository;
    }

    public Profile create(Profile profile) {
        if (profile == null) {
            throw new IllegalArgumentException("Profile cannot be null");
        }
        return profileRepository.save(profile);
    }

    public Profile findById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid profile ID");
        }
        return profileRepository.findById(id).orElseThrow(() -> new RuntimeException("Profile not found"));
    }

    public Profile update(Long id, Profile profile) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid profile ID");
        }
        if (profile == null) {
            throw new IllegalArgumentException("Profile cannot be null");
        }
        if (!profileRepository.existsById(id)) {
            throw new RuntimeException("Profile not found");
        }
        profile.setId(id);
        return profileRepository.save(profile);
    }

    public void delete(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid profile ID");
        }
        if (!profileRepository.existsById(id)) {
            throw new RuntimeException("Profile not found");
        }
        profileRepository.deleteById(id);
    }

}
