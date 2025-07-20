package com.coderscampus.cp.service;

import com.coderscampus.cp.domain.User;
import com.coderscampus.cp.dto.UserDTO;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

@Service
public class SessionManagerService {

    private final UserService userService;

    public SessionManagerService(UserService userService) {
        this.userService = userService;
    }

    /**
     * Authenticates a user and creates a session
     *
     * @param user The user to authenticate
     * @param session The HTTP session to store user information
     * @return UserDTO of the authenticated user
     */
    public UserDTO authenticate(User user, HttpSession session) {
        // Validate user
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        // Validate session
        if (session == null) {
            throw new IllegalArgumentException("HttpSession cannot be null");
        }
        // Check if user is already authenticated
        if (isAuthenticated(session)) {
            return getCurrentUser(session); // Return current user if already authenticated
        }
        // Set user as online
        user.setOnline(true);
        userService.save(user); // Save user to update online status
        // Create UserDTO from User
        UserDTO userDTO = new UserDTO(user);
        // Store user information in session
        session.setAttribute("uid", user.getUid());
        session.setAttribute("currentUser", userDTO);

        return userDTO;
    }

    /**
     * Checks if the current session is authenticated
     *
     * @param session The HTTP session to check
     * @return true if authenticated, false otherwise
     */
    public boolean isAuthenticated(HttpSession session) {
        if (session == null) {
            return false; // Fixed: should return false when session is null
        }

        String uid = (String) session.getAttribute("uid");
        UserDTO userDTO = (UserDTO) session.getAttribute("currentUser");

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
     * @param session The HTTP session
     * @return UserDTO of the current user or null if not authenticated
     */
    public UserDTO getCurrentUser(HttpSession session) {
        if (session == null) {
            return null;
        }
        return (UserDTO) session.getAttribute("currentUser");
    }

    /**
     * Gets the current user's UID from the session
     *
     * @param session The HTTP session
     * @return User's UID or null if not authenticated
     */
    public String getCurrentUserUid(HttpSession session) {
        if (session == null) {
            return null;
        }
        return (String) session.getAttribute("uid");
    }

    /**
     * Validates the session and refreshes user data if needed
     *
     * @param session The HTTP session to validate
     * @return true if session is valid, false otherwise
     */
    public boolean validateSession(HttpSession session) {
        if (!isAuthenticated(session)) { // Fixed: should check if NOT authenticated
            return false;
        }

        String uid = getCurrentUserUid(session);
        User user = userService.findByUid(uid);

        if (user == null || !Boolean.TRUE.equals(user.getOnline())) {
            invalidateSession(session);
            return false;
        }

        // Refresh user data in session if needed
        UserDTO currentUserDTO = getCurrentUser(session);
        UserDTO refreshedUserDTO = new UserDTO(user);

        if (!currentUserDTO.equals(refreshedUserDTO)) {
            session.setAttribute("currentUser", refreshedUserDTO);
        }

        return true;
    }

    /**
     * Logs out the user and invalidates the session
     *
     * @param session The HTTP session to invalidate
     */
    public void logout(HttpSession session) {
        if (session != null) {
            String uid = getCurrentUserUid(session);

            // Set user as offline
            if (uid != null) {
                User user = userService.findByUid(uid);
                if (user != null) {
                    user.setOnline(false);
                    userService.save(user);
                }
            }

            // Invalidate session
            invalidateSession(session);
        }
    }

    /**
     * Invalidates the current session
     *
     * @param session The HTTP session to invalidate
     */
    public void invalidateSession(HttpSession session) {
        if (session != null) {
            try {
                session.invalidate();
            } catch (IllegalStateException e) {
                // Session already invalidated
            }
        }
    }
}