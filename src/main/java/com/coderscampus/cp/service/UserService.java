package com.coderscampus.cp.service;

import com.coderscampus.cp.domain.User;
import com.coderscampus.cp.dto.AuthObjectDTO;
import com.coderscampus.cp.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    // This service can be used to handle user-related operations
    // such as authentication, authorization, and user management.
    // Currently, it is empty but can be expanded as needed.

    // Example methods could include:
    // - save(User user)
    // - findByUid(String uid)
    // - findByEmail(String email)
    // - delete(String uid)

    private final UserRepository userRepo;

    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public User save(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }

        if (user.getUid() == null || user.getUid().isEmpty()) {
            throw new IllegalArgumentException("User UID cannot be null or empty");
        }

        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            throw new IllegalArgumentException("User email cannot be null or empty");
        }

        if (user.getDisplayName() == null || user.getDisplayName().isEmpty()) {
            throw new IllegalArgumentException("User display name cannot be null or empty");
        }

        // Check if user exists by UID
        Optional<User> existingUser = userRepo.findByUid(user.getUid());

        if (existingUser.isPresent()) {
            // Update existing user
            User userToUpdate = existingUser.get();
            userToUpdate.setEmail(user.getEmail());
            userToUpdate.setDisplayName(user.getDisplayName());
            userToUpdate.setPhotoUrl(user.getPhotoUrl());
            userToUpdate.setUsername(user.getUsername());
            userToUpdate.setPassword(user.getPassword());
            userToUpdate.setActive(user.getActive());
            userToUpdate.setOnline(user.getOnline());
            userToUpdate.setRole(user.getRole());
            // updatedAt will be set automatically by @PreUpdate

            return userRepo.save(userToUpdate);
        } else {
            // Create new user
            // createdAt and updatedAt will be set automatically by @PrePersist
            return userRepo.save(user);
        }
    }

    public User findByUid(String uid) {
        if (uid == null || uid.isEmpty()) {
            return null;
        }
        return userRepo.findByUid(uid).orElse(null);
    }

    public User findById(Long id) {
        if (id == null) {
            return null;
        }
        return userRepo.findById(id).orElse(null);
    }

    public Boolean existsById(Long id) {
        if (id == null) {
            return false;
        }
        return userRepo.existsById(id);
    }

    /**
     * Gets or creates a user based on authentication data
     *
     * @param authDto The authentication object
     * @return User entity
     */
    public User getOrCreateUser(AuthObjectDTO authDto) {
        User user = userRepo.findByUid(authDto.getUid()).orElse(null);

        if (user == null) {
            user = new User();
            user.setUid(authDto.getUid());
            user.setEmail(authDto.getEmail());
            user.setDisplayName(authDto.getDisplayName());
            user.setPhotoUrl(authDto.getPhotoUrl());
            user.setRole(User.Role.ROLE_STUDENT);
        }

        // Update user properties
        user.setEmail(authDto.getEmail());
        user.setDisplayName(authDto.getDisplayName());
        user.setPhotoUrl(authDto.getPhotoUrl());
        user.setOnline(true);

        return userRepo.save(user);
    }

}
