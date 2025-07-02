package com.coderscampus.cp.service;

import com.coderscampus.cp.domain.User;
import com.coderscampus.cp.dto.AuthObjectDTO;
import com.coderscampus.cp.dto.UserDTO;
import com.coderscampus.cp.repository.UserRepository;
import jakarta.transaction.Transactional;
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
     * Retrieves a user by its unique identifier (UID) and returns it as a UserDTO.
     *
     * @param uid The unique identifier of the user to be retrieved
     * @return The UserDTO object if found
     */
    public UserDTO findDTOByUid(String uid) {
        User user = userRepository.findByUid(uid)
                .orElseThrow(() -> new RuntimeException("User not found with UID: " + uid));
        return new UserDTO(user);
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
     * Saves a user to the database. Creates a new user if ID is null, otherwise updates existing user.
     *
     * @param user The user object to be saved
     * @return The saved user object
     */
    public User save(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        return userRepository.save(user);
    }

    @Transactional
    public UserDTO saveByUid(UserDTO userDTO, AuthObjectDTO authDto) {
        if (authDto == null || userDTO == null) {
            throw new IllegalArgumentException("AuthObjectDTO and UserDTO cannot be null");
        }

        User existingUser = userRepository.findByUid(authDto.getUid()).orElse(new User());

        existingUser.setUid(authDto.getUid());
        existingUser.setEmail(authDto.getEmail());
        existingUser.setDisplayName(authDto.getDisplayName());
        existingUser.setEnabled(userDTO.getEnabled());
        existingUser.setOnline(userDTO.getOnline());

        User user = userRepository.save(existingUser);
        return new UserDTO(user);
    }

    /**
     * Updates an existing user by ID with new data.
     *
     * @param id   The ID of the user to be updated
     * @param user The updated user object
     * @return The updated user object
     */
    public User updateById(Long id, User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        User existingUser = findById(id);
        updateUserFields(existingUser, user);
        return userRepository.save(existingUser);
    }

    /**
     * Updates the fields of an existing user with data from another user object.
     *
     * @param existingUser The user to be updated
     * @param newUser      The user containing new data
     */
    private void updateUserFields(User existingUser, User newUser) {
        existingUser.setEmail(newUser.getEmail());
        // Add other fields as necessary
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