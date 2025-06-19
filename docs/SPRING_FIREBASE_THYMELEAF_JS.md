
# Firebase Authentication System with Spring Boot and Thymeleaf+JS

This implementation provides a complete Firebase authentication system for a Spring Boot application with Thymeleaf and JavaScript. The system supports email/password, Google, and GitHub login methods, with persistence, "remember me" functionality, and logout capabilities.

## 1. Project Dependencies

Add these dependencies to your `pom.xml`:

```xml
<!-- Firebase Admin SDK -->
<dependency>
    <groupId>com.google.firebase</groupId>
    <artifactId>firebase-admin</artifactId>
    <version>9.1.1</version>
</dependency>

<!-- Spring Security -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>

<!-- Thymeleaf Spring Security Extras -->
<dependency>
    <groupId>org.thymeleaf.extras</groupId>
    <artifactId>thymeleaf-extras-springsecurity6</artifactId>
</dependency>
```

## 2. Firebase Configuration

### 2.1. Service Account Key

Download your Firebase service account key from the Firebase Console and place it in `src/main/resources/firebase-service-account.json`.

### 2.2. Firebase Configuration Class

```java
package com.coderscampus.cp.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

@Configuration
public class FirebaseConfig {

    @Bean
    public FirebaseApp firebaseApp() throws IOException {
        if (FirebaseApp.getApps().isEmpty()) {
            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(
                            new ClassPathResource("firebase-service-account.json").getInputStream()))
                    .build();
            return FirebaseApp.initializeApp(options);
        }
        return FirebaseApp.getInstance();
    }
}
```

### 2.3. Firebase Client Configuration

Create a file `src/main/resources/static/js/firebase-config.js`:

```javascript
// Firebase configuration
const firebaseConfig = {
  apiKey: "YOUR_API_KEY",
  authDomain: "YOUR_PROJECT_ID.firebaseapp.com",
  projectId: "YOUR_PROJECT_ID",
  storageBucket: "YOUR_PROJECT_ID.appspot.com",
  messagingSenderId: "YOUR_MESSAGING_SENDER_ID",
  appId: "YOUR_APP_ID"
};

// Initialize Firebase
firebase.initializeApp(firebaseConfig);
```

## 3. Spring Security Configuration

```java
package com.coderscampus.cp.config;

import com.coderscampus.cp.security.FirebaseAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final FirebaseAuthenticationFilter firebaseAuthFilter;

    public SecurityConfig(FirebaseAuthenticationFilter firebaseAuthFilter) {
        this.firebaseAuthFilter = firebaseAuthFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/auth/**", "/js/**", "/css/**", "/images/**", "/dist/**", "/fbauth-element.bundled.js").permitAll()
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/auth/login")
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/auth/logout")
                .logoutSuccessUrl("/auth/login?logout")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
            )
            .addFilterBefore(firebaseAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
```

## 4. Firebase Authentication Filter

```java
package com.coderscampus.cp.security;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class FirebaseAuthenticationFilter extends OncePerRequestFilter {

    private final FirebaseAuth firebaseAuth;

    public FirebaseAuthenticationFilter(FirebaseAuth firebaseAuth) {
        this.firebaseAuth = firebaseAuth;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        // Skip filter for authentication endpoints
        String path = request.getRequestURI();
        if (path.startsWith("/auth/") || path.startsWith("/js/") || path.startsWith("/css/") || 
            path.startsWith("/images/") || path.startsWith("/dist/") || path.contains("fbauth-element.bundled.js")) {
            filterChain.doFilter(request, response);
            return;
        }

        // Check for Firebase ID token in session
        HttpSession session = request.getSession();
        String firebaseToken = (String) session.getAttribute("firebaseToken");
        
        if (firebaseToken != null) {
            try {
                // Verify the token
                FirebaseToken decodedToken = firebaseAuth.verifySessionCookie(firebaseToken, true);
                
                // Create authentication object
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    decodedToken.getUid(),
                    null,
                    Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))
                );
                
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch (FirebaseAuthException e) {
                // Token is invalid, clear session
                session.invalidate();
                response.sendRedirect("/auth/login");
                return;
            }
        } else {
            // No token found, redirect to login
            response.sendRedirect("/auth/login");
            return;
        }
        
        filterChain.doFilter(request, response);
    }
}
```

## 5. Firebase Authentication Service

```java
package com.coderscampus.cp.service;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.google.firebase.auth.SessionCookieOptions;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class FirebaseAuthService {

    private final FirebaseAuth firebaseAuth;

    public FirebaseAuthService(FirebaseAuth firebaseAuth) {
        this.firebaseAuth = firebaseAuth;
    }

    public void createSessionCookie(String idToken, boolean rememberMe, HttpServletRequest request) throws FirebaseAuthException {
        // Verify the ID token
        FirebaseToken decodedToken = firebaseAuth.verifyIdToken(idToken);
        
        // Set session expiration based on "remember me" option
        long expiresIn = rememberMe ? 
                TimeUnit.DAYS.toMillis(14) : // 14 days
                TimeUnit.HOURS.toMillis(24); // 24 hours
        
        // Create the session cookie
        String sessionCookie = firebaseAuth.createSessionCookie(idToken, SessionCookieOptions.builder()
                .setExpiresIn(expiresIn)
                .build());
        
        // Store user info in session
        HttpSession session = request.getSession();
        session.setAttribute("firebaseToken", sessionCookie);
        session.setAttribute("uid", decodedToken.getUid());
        session.setAttribute("email", decodedToken.getEmail());
        session.setAttribute("displayName", decodedToken.getName());
        
        // Set session timeout
        session.setMaxInactiveInterval((int) TimeUnit.MILLISECONDS.toSeconds(expiresIn));
    }

    public void revokeAllSessions(String uid) throws FirebaseAuthException {
        firebaseAuth.revokeRefreshTokens(uid);
    }
}
```

## 6. Authentication Controller

```java
package com.coderscampus.cp.web;

import com.coderscampus.cp.service.FirebaseAuthService;
import com.google.firebase.auth.FirebaseAuthException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final FirebaseAuthService firebaseAuthService;

    public AuthController(FirebaseAuthService firebaseAuthService) {
        this.firebaseAuthService = firebaseAuthService;
    }

    @GetMapping("/login")
    public String loginPage(Model model, @RequestParam(required = false) String error) {
        if (error != null) {
            model.addAttribute("error", "Authentication failed. Please try again.");
        }
        return "auth/login";
    }

    @PostMapping("/session")
    @ResponseBody
    public ResponseEntity<?> createSession(@RequestBody Map<String, Object> request, HttpServletRequest httpRequest) {
        try {
            String idToken = (String) request.get("idToken");
            boolean rememberMe = request.get("rememberMe") != null && (boolean) request.get("rememberMe");
            
            firebaseAuthService.createSessionCookie(idToken, rememberMe, httpRequest);
            
            return ResponseEntity.ok().build();
        } catch (FirebaseAuthException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/register")
    public String registerPage() {
        return "auth/register";
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        String uid = (String) session.getAttribute("uid");
        if (uid != null) {
            try {
                firebaseAuthService.revokeAllSessions(uid);
            } catch (FirebaseAuthException e) {
                // Log error but continue with logout
            }
        }
        
        session.invalidate();
        return "redirect:/auth/login?logout";
    }
}
```

## 7. Thymeleaf Templates

### 7.1. Login Page (src/main/resources/templates/auth/login.html)

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org" lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login - Coder Packaging</title>
    <link rel="stylesheet" href="/dist/css/output.css" th:href="@{/dist/css/output.css}" type="text/css">
    
    <!-- Firebase SDK -->
    <script src="https://www.gstatic.com/firebasejs/9.6.10/firebase-app-compat.js"></script>
    <script src="https://www.gstatic.com/firebasejs/9.6.10/firebase-auth-compat.js"></script>
    <script src="/js/firebase-config.js"></script>
</head>
<body class="bg-gray-100 flex items-center justify-center min-h-screen">
    <div class="bg-white p-8 rounded-lg shadow-md w-full max-w-md">
        <div class="text-center mb-8">
            <h1 class="text-3xl font-bold mb-2">Welcome Back</h1>
            <p class="text-gray-600">Sign in to continue to your account</p>
        </div>
        
        <div id="error-message" class="bg-red-100 border border-red-400 text-red-700 px-4 py-3 rounded mb-4 hidden" role="alert">
            <span id="error-text"></span>
        </div>
        
        <form id="email-login-form" class="space-y-4">
            <div>
                <label for="email" class="block text-sm font-medium text-gray-700">Email</label>
                <input type="email" id="email" name="email" required 
                       class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500">
            </div>
            
            <div>
                <label for="password" class="block text-sm font-medium text-gray-700">Password</label>
                <input type="password" id="password" name="password" required 
                       class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500">
            </div>
            
            <div class="flex items-center justify-between">
                <div class="flex items-center">
                    <input id="remember-me" name="remember-me" type="checkbox" 
                           class="h-4 w-4 text-indigo-600 focus:ring-indigo-500 border-gray-300 rounded">
                    <label for="remember-me" class="ml-2 block text-sm text-gray-900">
                        Remember me
                    </label>
                </div>
                
                <div class="text-sm">
                    <a href="#" class="font-medium text-indigo-600 hover:text-indigo-500">
                        Forgot password?
                    </a>
                </div>
            </div>
            
            <div>
                <button type="submit" 
                        class="w-full flex justify-center py-2 px-4 border border-transparent rounded-md shadow-sm text-sm font-medium text-white bg-indigo-600 hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500">
                    Sign In
                </button>
            </div>
        </form>
        
        <div class="mt-6">
            <div class="relative">
                <div class="absolute inset-0 flex items-center">
                    <div class="w-full border-t border-gray-300"></div>
                </div>
                <div class="relative flex justify-center text-sm">
                    <span class="px-2 bg-white text-gray-500">Or continue with</span>
                </div>
            </div>
            
            <div class="mt-6 grid grid-cols-2 gap-3">
                <button id="google-login" type="button" 
                        class="w-full inline-flex justify-center py-2 px-4 border border-gray-300 rounded-md shadow-sm bg-white text-sm font-medium text-gray-500 hover:bg-gray-50">
                    <svg class="w-5 h-5 mr-2" viewBox="0 0 24 24" fill="currentColor">
                        <path d="M12.545,10.239v3.821h5.445c-0.712,2.315-2.647,3.972-5.445,3.972c-3.332,0-6.033-2.701-6.033-6.032s2.701-6.032,6.033-6.032c1.498,0,2.866,0.549,3.921,1.453l2.814-2.814C17.503,2.988,15.139,2,12.545,2C7.021,2,2.543,6.477,2.543,12s4.478,10,10.002,10c8.396,0,10.249-7.85,9.426-11.748L12.545,10.239z"/>
                    </svg>
                    Google
                </button>
                
                <button id="github-login" type="button" 
                        class="w-full inline-flex justify-center py-2 px-4 border border-gray-300 rounded-md shadow-sm bg-white text-sm font-medium text-gray-500 hover:bg-gray-50">
                    <svg class="w-5 h-5 mr-2" viewBox="0 0 24 24" fill="currentColor">
                        <path d="M12 0c-6.626 0-12 5.373-12 12 0 5.302 3.438 9.8 8.207 11.387.599.111.793-.261.793-.577v-2.234c-3.338.726-4.033-1.416-4.033-1.416-.546-1.387-1.333-1.756-1.333-1.756-1.089-.745.083-.729.083-.729 1.205.084 1.839 1.237 1.839 1.237 1.07 1.834 2.807 1.304 3.492.997.107-.775.418-1.305.762-1.604-2.665-.305-5.467-1.334-5.467-5.931 0-1.311.469-2.381 1.236-3.221-.124-.303-.535-1.524.117-3.176 0 0 1.008-.322 3.301 1.23.957-.266 1.983-.399 3.003-.404 1.02.005 2.047.138 3.006.404 2.291-1.552 3.297-1.23 3.297-1.23.653 1.653.242 2.874.118 3.176.77.84 1.235 1.911 1.235 3.221 0 4.609-2.807 5.624-5.479 5.921.43.372.823 1.102.823 2.222v3.293c0 .319.192.694.801.576 4.765-1.589 8.199-6.086 8.199-11.386 0-6.627-5.373-12-12-12z"/>
                    </svg>
                    GitHub
                </button>
            </div>
        </div>
        
        <div class="mt-6 text-center">
            <p class="text-sm text-gray-600">
                Don't have an account? 
                <a href="/auth/register" class="font-medium text-indigo-600 hover:text-indigo-500">
                    Sign up
                </a>
            </p>
        </div>
    </div>
    
    <script src="/js/auth.js"></script>
</body>
</html>
```

### 7.2. Registration Page (src/main/resources/templates/auth/register.html)

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org" lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Register - Coder Packaging</title>
    <link rel="stylesheet" href="/dist/css/output.css" th:href="@{/dist/css/output.css}" type="text/css">
    
    <!-- Firebase SDK -->
    <script src="https://www.gstatic.com/firebasejs/9.6.10/firebase-app-compat.js"></script>
    <script src="https://www.gstatic.com/firebasejs/9.6.10/firebase-auth-compat.js"></script>
    <script src="/js/firebase-config.js"></script>
</head>
<body class="bg-gray-100 flex items-center justify-center min-h-screen">
    <div class="bg-white p-8 rounded-lg shadow-md w-full max-w-md">
        <div class="text-center mb-8">
            <h1 class="text-3xl font-bold mb-2">Create Account</h1>
            <p class="text-gray-600">Sign up to get started</p>
        </div>
        
        <div id="error-message" class="bg-red-100 border border-red-400 text-red-700 px-4 py-3 rounded mb-4 hidden" role="alert">
            <span id="error-text"></span>
        </div>
        
        <form id="register-form" class="space-y-4">
            <div>
                <label for="display-name" class="block text-sm font-medium text-gray-700">Full Name</label>
                <input type="text" id="display-name" name="displayName" required 
                       class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500">
            </div>
            
            <div>
                <label for="email" class="block text-sm font-medium text-gray-700">Email</label>
                <input type="email" id="email" name="email" required 
                       class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500">
            </div>
            
            <div>
                <label for="password" class="block text-sm font-medium text-gray-700">Password</label>
                <input type="password" id="password" name="password" required 
                       class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500">
            </div>
            
            <div>
                <label for="confirm-password" class="block text-sm font-medium text-gray-700">Confirm Password</label>
                <input type="password" id="confirm-password" name="confirmPassword" required 
                       class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500">
            </div>
            
            <div class="flex items-center">
                <input id="remember-me" name="remember-me" type="checkbox" 
                       class="h-4 w-4 text-indigo-600 focus:ring-indigo-500 border-gray-300 rounded">
                <label for="remember-me" class="ml-2 block text-sm text-gray-900">
                    Remember me
                </label>
            </div>
            
            <div>
                <button type="submit" 
                        class="w-full flex justify-center py-2 px-4 border border-transparent rounded-md shadow-sm text-sm font-medium text-white bg-indigo-600 hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500">
                    Sign Up
                </button>
            </div>
        </form>
        
        <div class="mt-6">
            <div class="relative">
                <div class="absolute inset-0 flex items-center">
                    <div class="w-full border-t border-gray-300"></div>
                </div>
                <div class="relative flex justify-center text-sm">
                    <span class="px-2 bg-white text-gray-500">Or continue with</span>
                </div>
            </div>
            
            <div class="mt-6 grid grid-cols-2 gap-3">
                <button id="google-login" type="button" 
                        class="w-full inline-flex justify-center py-2 px-4 border border-gray-300 rounded-md shadow-sm bg-white text-sm font-medium text-gray-500 hover:bg-gray-50">
                    <svg class="w-5 h-5 mr-2" viewBox="0 0 24 24" fill="currentColor">
                        <path d="M12.545,10.239v3.821h5.445c-0.712,2.315-2.647,3.972-5.445,3.972c-3.332,0-6.033-2.701-6.033-6.032s2.701-6.032,6.033-6.032c1.498,0,2.866,0.549,3.921,1.453l2.814-2.814C17.503,2.988,15.139,2,12.545,2C7.021,2,2.543,6.477,2.543,12s4.478,10,10.002,10c8.396,0,10.249-7.85,9.426-11.748L12.545,10.239z"/>
                    </svg>
                    Google
                </button>
                
                <button id="github-login" type="button" 
                        class="w-full inline-flex justify-center py-2 px-4 border border-gray-300 rounded-md shadow-sm bg-white text-sm font-medium text-gray-500 hover:bg-gray-50">
                    <svg class="w-5 h-5 mr-2" viewBox="0 0 24 24" fill="currentColor">
                        <path d="M12 0c-6.626 0-12 5.373-12 12 0 5.302 3.438 9.8 8.207 11.387.599.111.793-.261.793-.577v-2.234c-3.338.726-4.033-1.416-4.033-1.416-.546-1.387-1.333-1.756-1.333-1.756-1.089-.745.083-.729.083-.729 1.205.084 1.839 1.237 1.839 1.237 1.07 1.834 2.807 1.304 3.492.997.107-.775.418-1.305.762-1.604-2.665-.305-5.467-1.334-5.467-5.931 0-1.311.469-2.381 1.236-3.221-.124-.303-.535-1.524.117-3.176 0 0 1.008-.322 3.301 1.23.957-.266 1.983-.399 3.003-.404 1.02.005 2.047.138 3.006.404 2.291-1.552 3.297-1.23 3.297-1.23.653 1.653.242 2.874.118 3.176.77.84 1.235 1.911 1.235 3.221 0 4.609-2.807 5.624-5.479 5.921.43.372.823 1.102.823 2.222v3.293c0 .319.192.694.801.576 4.765-1.589 8.199-6.086 8.199-11.386 0-6.627-5.373-12-12-12z"/>
                    </svg>
                    GitHub
                </button>
            </div>
        </div>
        
        <div class="mt-6 text-center">
            <p class="text-sm text-gray-600">
                Already have an account? 
                <a href="/auth/login" class="font-medium text-indigo-600 hover:text-indigo-500">
                    Sign in
                </a>
            </p>
        </div>
    </div>
    
    <script src="/js/auth.js"></script>
</body>
</html>
```

## 8. JavaScript Authentication Logic

Create a file `src/main/resources/static/js/auth.js`:

```javascript
document.addEventListener('DOMContentLoaded', function() {
    // Initialize error message display
    const errorMessage = document.getElementById('error-message');
    const errorText = document.getElementById('error-text');
    
    // Email/Password Login
    const emailLoginForm = document.getElementById('email-login-form');
    if (emailLoginForm) {
        emailLoginForm.addEventListener('submit', function(e) {
            e.preventDefault();
            
            const email = document.getElementById('email').value;
            const password = document.getElementById('password').value;
            const rememberMe = document.getElementById('remember-me').checked;
            
            // Clear previous error messages
            hideError();
            
            // Set persistence based on "remember me" option
            const persistence = rememberMe ? 
                firebase.auth.Auth.Persistence.LOCAL : 
                firebase.auth.Auth.Persistence.SESSION;
            
            firebase.auth().setPersistence(persistence)
                .then(() => {
                    return firebase.auth().signInWithEmailAndPassword(email, password);
                })
                .then((userCredential) => {
                    // Get ID token for server-side session
                    return userCredential.user.getIdToken();
                })
                .then((idToken) => {
                    // Send token to server to create session
                    return fetch('/auth/session', {
                        method: 'POST',
                        headers: {
                            'Content-Type': 'application/json'
                        },
                        body: JSON.stringify({ idToken, rememberMe })
                    });
                })
                .then((response) => {
                    if (response.ok) {
                        window.location.href = '/dashboard';
                    } else {
                        throw new Error('Failed to create session');
                    }
                })
                .catch((error) => {
                    console.error('Login error:', error);
                    showError(getErrorMessage(error));
                });
        });
    }
    
    // Registration Form
    const registerForm = document.getElementById('register-form');
    if (registerForm) {
        registerForm.addEventListener('submit', function(e) {
            e.preventDefault();
            
            const displayName = document.getElementById('display-name').value;
            const email = document.getElementById('email').value;
            const password = document.getElementById('password').value;
            const confirmPassword = document.getElementById('confirm-password').value;
            const rememberMe = document.getElementById('remember-me').checked;
            
            // Clear previous error messages
            hideError();
            
            // Validate passwords match
            if (password !== confirmPassword) {
                showError('Passwords do not match');
                return;
            }
            
            // Create user with email and password
            firebase.auth().createUserWithEmailAndPassword(email, password)
                .then((userCredential) => {
                    // Update profile with display name
                    return userCredential.user.updateProfile({
                        displayName: displayName
                    }).then(() => userCredential.user);
                })
                .then((user) => {
                    // Get ID token for server-side session
                    return user.getIdToken();
                })
                .then((idToken) => {
                    // Send token to server to create session
                    return fetch('/auth/session', {
                        method: 'POST',
                        headers: {
                            'Content-Type': 'application/json'
                        },
                        body: JSON.stringify({ idToken, rememberMe })
                    });
                })
                .then((response) => {
                    if (response.ok) {
                        window.location.href = '/dashboard';
                    } else {
                        throw new Error('Failed to create session');
                    }
                })
                .catch((error) => {
                    console.error('Registration error:', error);
                    showError(getErrorMessage(error));
                });
        });
    }
    
    // Google Login
    const googleLoginBtn = document.getElementById('google-login');
    if (googleLoginBtn) {
        googleLoginBtn.addEventListener('click', function() {
            const rememberMe = document.getElementById('remember-me')?.checked || false;
            
            // Clear previous error messages
            hideError();
            
            const provider = new firebase.auth.GoogleAuthProvider();
            
            firebase.auth().signInWithPopup(provider)
                .then((result) => {
                    return result.user.getIdToken();
                })
                .then((idToken) => {
                    return fetch('/auth/session', {
                        method: 'POST',
                        headers: {
                            'Content-Type': 'application/json'
                        },
                        body: JSON.stringify({ idToken, rememberMe })
                    });
                })
                .then((response) => {
                    if (response.ok) {
                        window.location.href = '/dashboard';
                    } else {
                        throw new Error('Failed to create session');
                    }
                })
                .catch((error) => {
                    console.error('Google login error:', error);
                    showError(getErrorMessage(error));
                });
        });
    }
    
    // GitHub Login
    const githubLoginBtn = document.getElementById('github-login');
    if (githubLoginBtn) {
        githubLoginBtn.addEventListener('click', function() {
            const rememberMe = document.getElementById('remember-me')?.checked || false;
            
            // Clear previous error messages
            hideError();
            
            const provider = new firebase.auth.GithubAuthProvider();
            
            firebase.auth().signInWithPopup(provider)
                .then((result) => {
                    return result.user.getIdToken();
                })
                .then((idToken) => {
                    return fetch('/auth/session', {
                        method: 'POST',
                        headers: {
                            'Content-Type': 'application/json'
                        },
                        body: JSON.stringify({ idToken, rememberMe })
                    });
                })
                .then((response) => {
                    if (response.ok) {
                        window.location.href = '/dashboard';
                    } else {
                        throw new Error('Failed to create session');
                    }
                })
                .catch((error) => {
                    console.error('GitHub login error:', error);
                    showError(getErrorMessage(error));
                });
        });
    }
    
    // Helper function to show error message
    function showError(message) {
        errorText.textContent = message;
        errorMessage.classList.remove('hidden');
    }
    
    // Helper function to hide error message
    function hideError() {
        errorText.textContent = '';
        errorMessage.classList.add('hidden');
    }
    
    // Helper function to get user-friendly error messages
    function getErrorMessage(error) {
        switch (error.code) {
            case 'auth/invalid-email':
                return 'Invalid email address format.';
            case 'auth/user-disabled':
                return 'This account has been disabled.';
            case 'auth/user-not-found':
                return 'No account found with this email.';
            case 'auth/wrong-password':
                return 'Incorrect password.';
            case 'auth/email-already-in-use':
                return 'Email address is already in use.';
            case 'auth/weak-password':
                return 'Password is too weak. Please use a stronger password.';
            case 'auth/popup-closed-by-user':
                return 'Login canceled. Please try again.';
            case 'auth/account-exists-with-different-credential':
                return 'An account already exists with the same email address but different sign-in credentials.';
            default:
                return error.message || 'An unknown error occurred. Please try again.';
        }
    }
});
```

## 9. Logout Component

Add a logout button to your dashboard layout or navigation bar:

```html
<form th:action="@{/auth/logout}" method="post" class="inline">
    <button type="submit" class="text-red-600 hover:text-red-800">
        <svg class="w-5 h-5 inline-block mr-1" fill="none" stroke="currentColor" viewBox="0 0 24 24">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M17 16l4-4m0 0l-4-4m4 4H7m6 4v1a3 3 0 01-3 3H6a3 3 0 01-3-3V7a3 3 0 013-3h4a3 3 0 013 3v1"></path>
        </svg>
        Logout
    </button>
</form>
```

## 10. Update Dashboard Layout

Update the dashboard layout to display the authenticated user's information:

```html
<!-- In the sidebar user section (around line 52-62) -->
<li>
    <a href="/profile">
        <img class="avatar avatar-md avatar-circle"
             src="https://ui-avatars.com/api/?name=[[${session.displayName}]]&background=random"
             alt="">
        <p>
            <span th:text="${session.displayName}">User Name</span>
            <span th:text="${session.email}">user@example.com</span>
        </p>
    </a>
</li>
```

## 11. Firebase Authentication Bean

Add a FirebaseAuth bean to your FirebaseConfig class:

```java
@Bean
public FirebaseAuth firebaseAuth() throws IOException {
    firebaseApp(); // Ensure Firebase is initialized
    return FirebaseAuth.getInstance();
}
```

## 12. Application Properties

Update your `application.properties` or `application.yml` file:

```properties
# Session Configuration
server.servlet.session.timeout=1d
server.servlet.session.cookie.http-only=true
server.servlet.session.cookie.secure=true

# Spring Security
spring.security.filter.order=10

# Firebase Configuration
firebase.database.url=https://YOUR_PROJECT_ID.firebaseio.com
```

## 13. Complete Implementation

This implementation provides a complete Firebase authentication system for a Spring Boot application with Thymeleaf and JavaScript. The system supports:

1. Email/password authentication
2. Google authentication
3. GitHub authentication
4. "Remember me" functionality
5. User data persistence in session
6. Secure logout

The implementation uses Spring Security for protecting routes and Firebase Authentication for the actual authentication process. The user's authentication state is maintained in both the client-side Firebase Auth and the server-side session.

When a user logs in, the client-side Firebase Auth generates an ID token, which is sent to the server. The server verifies the token, creates a session cookie, and stores user information in the session. This allows the user to remain authenticated even if the page is refreshed or the browser is closed (if "remember me" is enabled).

The logout process revokes all Firebase refresh tokens for the user and invalidates the session, ensuring complete logout from both client and server.