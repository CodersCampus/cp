package com.coderscampus.cp.service;

import com.coderscampus.cp.domain.User;
import com.coderscampus.cp.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Retrieves all users from the database.
     *
     * @return An iterable list of all users
     */
    public List<User> findAll() {
        return userRepository.findAll();
    }

    /**
     * Retrieves a user by its ID.
     *
     * @param id The ID of the user to be retrieved
     * @return The user object if found
     */
    public User findById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
    }

    /**
     * Retrieves a user by its unique identifier (UID).
     *
     * @param uid The unique identifier of the user to be retrieved
     * @return The user object if found
     */
    public User findByUid(String uid) {
        if (uid == null || uid.isEmpty()) {
            throw new IllegalArgumentException("UID cannot be null or empty");
        }
        return userRepository.findByUid(uid)
                .orElseThrow(() -> new RuntimeException("User not found with UID: " + uid));
    }

    /**
     * Retrieves a user by its email.
     *
     * @param email The email of the user to be retrieved
     * @return The user object if found
     */
    public User findByEmail(String email) {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));
    }

    /**
     * Creates a new user by saving it to the database.
     *
     * @param user The user object to be created
     * @return The created user object
     */
    public User create(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        return userRepository.save(user);
    }

    /**
     * Updates an existing user by saving it to the database.
     *
     * @param id The ID of the user to be updated
     * @param user The updated user object
     * @return The updated user object
     */
    public User update(Long id, User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        User existingUser = findById(id);
        existingUser.setEmail(user.getEmail());
        // Add other fields as necessary
        return userRepository.save(existingUser);
    }

    /**
     * Deletes a user by its ID.
     *
     * @param id The ID of the user to be deleted
     */
    public void delete(Long id) {
        User user = findById(id);
        userRepository.delete(user);
    }
}
