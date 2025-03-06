package myrpg.backend.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;
import myrpg.backend.api.dto.UserResponse;
import myrpg.backend.api.model.User;


@Service
public class SessionService {

    // Create a session and store an attribute
    public String createSession(HttpSession session, User user) {
        // Set a session attribute (e.g., username)
        session.setAttribute("LOGGED_IN", user.getId());

        // Retrieve and return the session ID
        String sessionId = session.getId();
        String message = "Session created with ID: " + sessionId + " For user: " + session.getAttribute("LOGGED_IN");
        System.out.println(message);
        return message;
    }

    // Retrieve session attribute
    public Long getSession(HttpSession session) {
        // Get the session attribute (username)
        Long foundUserId = (Long) session.getAttribute("LOGGED_IN");

        
        // If no session exists, return an error message
        if (foundUserId == null) {
            System.out.println("No user session found.");
            return null;
        }

        System.out.println("Found user, ID: " + foundUserId);

        // Return session data to the client
        return foundUserId;

    }

    // Invalidate the session

    public ResponseEntity invalidateSession(HttpSession session) {
        // Invalidate the session
        session.invalidate();
        return ResponseEntity.ok("Session invalidated");
    }
}

