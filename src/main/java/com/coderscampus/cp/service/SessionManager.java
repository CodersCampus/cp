package com.coderscampus.cp.service;

import com.coderscampus.cp.domain.User;
import com.coderscampus.cp.dto.AuthObjectDTO;
import com.coderscampus.cp.dto.UserDTO;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

@Service
public class SessionManager {

    private final UserService userService;

    public SessionManager(UserService userService) {
        this.userService = userService;
    }

    /**
     * Authenticates a user and creates a session
     *
     * @param authDto     The authentication object containing user details
     * @param httpSession The HTTP session to store user information
     * @return UserDTO of the authenticated user
     */
    public UserDTO authenticate(AuthObjectDTO authDto, HttpSession httpSession) {
        if (authDto == null) {
            throw new IllegalArgumentException("AuthObjectDTO cannot be null");
        }

        User user = getOrCreateUser(authDto);
        UserDTO userDTO = new UserDTO(user);

        // Store user information in session
        httpSession.setAttribute("uid", authDto.getUid());
        httpSession.setAttribute("currentUser", userDTO);

        return userDTO;
    }

    /**
     * Checks if the current session is authenticated
     *
     * @param httpSession The HTTP session to check
     * @return true if authenticated, false otherwise
     */
    public boolean isAuthenticated(HttpSession httpSession) {
        if (httpSession == null) {
            return false; // Fixed: should return false when session is null
        }

        String uid = (String) httpSession.getAttribute("uid");
        UserDTO userDTO = (UserDTO) httpSession.getAttribute("currentUser");

        if (uid == null || userDTO == null) {
            return false; // Fixed: should return false when uid or userDTO is null
        }

        // Validate that the user still exists and is online
        User user = userService.findByUid(uid);
        return user != null && Boolean.TRUE.equals(user.getOnline()); // Fixed: should return true only when user exists and is online
    }

    /**
     * Gets the current user from the session
     *
     * @param httpSession The HTTP session
     * @return UserDTO of the current user or null if not authenticated
     */
    public UserDTO getCurrentUser(HttpSession httpSession) {
        if (httpSession == null) {
            return null;
        }
        return (UserDTO) httpSession.getAttribute("currentUser");
    }

    /**
     * Gets the current user's UID from the session
     *
     * @param httpSession The HTTP session
     * @return User's UID or null if not authenticated
     */
    public String getCurrentUserUid(HttpSession httpSession) {
        if (httpSession == null) {
            return null;
        }
        return (String) httpSession.getAttribute("uid");
    }

    /**
     * Validates the session and refreshes user data if needed
     *
     * @param httpSession The HTTP session to validate
     * @return true if session is valid, false otherwise
     */
    public boolean validateSession(HttpSession httpSession) {
        if (!isAuthenticated(httpSession)) { // Fixed: should check if NOT authenticated
            return false;
        }

        String uid = getCurrentUserUid(httpSession);
        User user = userService.findByUid(uid);

        if (user == null || !Boolean.TRUE.equals(user.getOnline())) {
            invalidateSession(httpSession);
            return false;
        }

        // Refresh user data in session if needed
        UserDTO currentUserDTO = getCurrentUser(httpSession);
        UserDTO refreshedUserDTO = new UserDTO(user);

        if (!currentUserDTO.equals(refreshedUserDTO)) {
            httpSession.setAttribute("currentUser", refreshedUserDTO);
        }

        return true;
    }

    /**
     * Logs out the user and invalidates the session
     *
     * @param httpSession The HTTP session to invalidate
     */
    public void logout(HttpSession httpSession) {
        if (httpSession != null) {
            String uid = getCurrentUserUid(httpSession);

            // Set user as offline
            if (uid != null) {
                User user = userService.findByUid(uid);
                if (user != null) {
                    user.setOnline(false);
                    userService.save(user);
                }
            }

            // Invalidate session
            invalidateSession(httpSession);
        }
    }

    /**
     * Invalidates the current session
     *
     * @param httpSession The HTTP session to invalidate
     */
    public void invalidateSession(HttpSession httpSession) {
        if (httpSession != null) {
            try {
                httpSession.invalidate();
            } catch (IllegalStateException e) {
                // Session already invalidated
            }
        }
    }

    /**
     * Updates user session data
     *
     * @param httpSession The HTTP session
     * @param userDTO     The updated user data
     */
    public void updateUserSession(HttpSession httpSession, UserDTO userDTO) {
        if (httpSession != null && userDTO != null) {
            httpSession.setAttribute("currentUser", userDTO);
            if (userDTO.getId() != null && userService.existsById(userDTO.getId())) {
                // Update UID in session if user exists
                User user = userService.findById(userDTO.getId());
                httpSession.setAttribute("uid", user.getUid());
            }
        }
    }

    /**
     * Checks if user has a specific role
     *
     * @param httpSession The HTTP session
     * @param role        The role to check
     * @return true if user has the role, false otherwise
     */
    public boolean hasRole(HttpSession httpSession, User.Role role) {
        UserDTO userDTO = getCurrentUser(httpSession);
        if (userDTO == null) {
            return false;
        }

        Long id = userDTO.getId();
        User user = userService.findById(id);
        return user != null && role.equals(user.getRole());
    }

    /**
     * Gets or creates a user based on authentication data
     *
     * @param authDto The authentication object
     * @return User entity
     */
    private User getOrCreateUser(AuthObjectDTO authDto) {
        User user = userService.findByUid(authDto.getUid());

        if (user == null) {
            user = new User();
            user.setUid(authDto.getUid());
            user.setRole(User.Role.ROLE_STUDENT);
        }

        // Update user properties
        user.setEmail(authDto.getEmail());
        user.setDisplayName(authDto.getDisplayName());
        user.setPhotoUrl(authDto.getPhotoUrl());
        user.setOnline(true);

        return userService.save(user);
    }
}