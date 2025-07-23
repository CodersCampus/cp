package com.coderscampus.cp.service;

import com.coderscampus.cp.domain.Profile;
import com.coderscampus.cp.domain.User;
import com.coderscampus.cp.repository.ProfileRepository;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {
    private final ProfileRepository profileRepo;

    public ProfileService(ProfileRepository profileRepo) {
        this.profileRepo = profileRepo;
    }

    public Profile save(Profile profile) {
        validateProfile(profile);

        // Ensure the profile has a valid user before saving
        if (profile.getUser() == null) {
            throw new IllegalArgumentException("Profile must have a valid user");
        }

        // If this is a new profile, ensure the ID matches the user ID
        if (profile.getId() == null) {
            profile.setId(profile.getUser().getId());
        } else {
            validateId(profile.getId());
            // For existing profiles, ensure ID consistency
            if (!profile.getId().equals(profile.getUser().getId())) {
                throw new IllegalArgumentException("Profile ID must match User ID");
            }
        }

        return profileRepo.save(profile);
    }

    // New method that returns null instead of throwing an exception
    public Profile findById(Long id) {
        if (id == null || id <= 0) {
            return null;
        }
        return profileRepo.findById(id).orElse(null);
    }

    public void delete(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid profile ID");
        }
        if (!profileRepo.existsById(id)) {
            throw new RuntimeException("Profile not found");
        }
        profileRepo.deleteById(id);
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

    /**
     * Ensures that a profile exists for the given user, creating one if necessary
     *
     * @param user The user for whom to ensure a profile exists
     */
    public void ensureUserProfileExists(User user) {
        Profile profile = findById(user.getId());
        if (profile == null) {
            profile = new Profile();
            profile.setUser(user);
            profileRepo.save(profile);
        }
    }

}