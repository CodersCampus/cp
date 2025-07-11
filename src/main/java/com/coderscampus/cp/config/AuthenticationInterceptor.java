package com.coderscampus.cp.config;

import com.coderscampus.cp.dto.UserDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * Interceptor to handle authentication for all requests.
 * This ensures that authentication data is properly set in the session
 * regardless of which page the user visits first.
 */
@Component
public class AuthenticationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // Always allow the request to proceed
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // Skip for API calls and resources
        String requestURI = request.getRequestURI();
        if (requestURI.startsWith("/api") ||
                requestURI.contains(".") ||
                requestURI.equals("/send-oauth") ||
                requestURI.equals("/logout")) {
            return;
        }

        // If ModelAndView is null or is a redirect, skip
        if (modelAndView == null || modelAndView.getViewName() == null || modelAndView.getViewName().startsWith("redirect:")) {
            return;
        }

        // Add authentication check to the model for all views
        HttpSession session = request.getSession();
        String uid = (String) session.getAttribute("uid");
        UserDTO user = (UserDTO) session.getAttribute("currentUser");

        if (modelAndView.getModel() != null) {
            modelAndView.getModel().put("isAuthenticated", uid != null && user != null);
            if (user != null) {
                modelAndView.getModel().put("user", user);
            }
        }
    }
}