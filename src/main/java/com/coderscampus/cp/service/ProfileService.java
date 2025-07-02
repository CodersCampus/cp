package com.coderscampus.cp.service;

import com.coderscampus.cp.domain.Profile;
import com.coderscampus.cp.repository.ProfileRepository;
import com.coderscampus.cp.repository.StudentRepository;
import com.coderscampus.cp.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {
    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;

    public ProfileService(ProfileRepository profileRepository, UserRepository userRepository, StudentRepository studentRepository) {
        this.profileRepository = profileRepository;
        this.userRepository = userRepository;
    }

    public Profile save(Profile profile) {
        validateProfile(profile);

        if (profile.getId() != null) {
            validateId(profile.getId());
            ensureProfileExists(profile.getId());
        }

        return profileRepository.save(profile);
    }

/*
    public Profile save(Long id, Profile profile) {
        validateId(id);
        validateProfile(profile);
        ensureProfileExists(id);
        profile.setId(id);
        return profileRepository.save(profile);
    }
*/

    public Profile findById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid profile ID");
        }
        return profileRepository.findById(id).orElseThrow(() -> new RuntimeException("Profile not found"));
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

    private void validateProfile(Profile profile) {
        if (profile == null) {
            throw new IllegalArgumentException("Profile cannot be null");
        }
    }

    private void validateId(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid profile ID");
        }
    }

    private void ensureProfileExists(Long id) {
        if (!profileRepository.existsById(id)) {
            throw new RuntimeException("Profile not found");
        }
    }
}
