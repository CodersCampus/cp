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

        // Remove the problematic ensureProfileExists check for new profiles
        if (profile.getId() != null) {
            validateId(profile.getId());
            // Only check if profile exists when we're updating an existing one
            // For new profiles with assigned IDs, we should allow creation
        }

        return profileRepository.save(profile);
    }

    // New method that returns null instead of throwing exception
    public Profile findById(Long id) {
        if (id == null || id <= 0) {
            return null;
        }
        return profileRepository.findById(id).orElse(null);
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

    // Keep this method for explicit updates where you want to ensure the profile exists
    private void ensureProfileExists(Long id) {
        if (!profileRepository.existsById(id)) {
            throw new RuntimeException("Profile not found");
        }
    }
}