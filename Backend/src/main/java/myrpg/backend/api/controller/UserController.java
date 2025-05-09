package myrpg.backend.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;
import myrpg.backend.api.dto.AuthenticationRequest;
import myrpg.backend.api.dto.UserRequest;
import myrpg.backend.api.dto.UserResponse;
import myrpg.backend.service.UserService;



@RestController
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/api/users")
    public List<UserResponse> getUser() {
        return userService.getUser();
    }

    @CrossOrigin(origins = "http://localhost:4200") // Allow frontend access
    @GetMapping("/api/users/{id}")
    public UserResponse getUser(@PathVariable Long id) {
        System.out.println(id);
    	return userService.getUser(id);
    }
   
    @CrossOrigin(origins = "http://localhost:4200") // Allow frontend access
    @PostMapping("/api/users")
    public UserResponse createUser(@RequestBody UserRequest request, HttpSession session) {
        return userService.createUser(request, session);
    }

    @CrossOrigin(origins = "http://localhost:4200") // Allow frontend access
    @PatchMapping("/api/users")
    public UserResponse updateUser(@RequestBody UserResponse request, HttpSession session) {
        return userService.updateUser(request);
    }

    @CrossOrigin(origins = "http://localhost:4200") // Allow frontend access
    @DeleteMapping("/api/users/{id}")
    public void deleteUser(@PathVariable Long id) {
    	userService.deleteUser(id);
    }
    
    @CrossOrigin(origins = "http://localhost:4200") // Allow frontend access
    @PostMapping("/api/users/authenticate")
    public UserResponse authenticateUser(@RequestBody AuthenticationRequest authenticationRequest, HttpSession session) {
        return userService.authenticate(authenticationRequest, session);
    }

    @CrossOrigin(origins = "http://localhost:4200") // Allow frontend access
    @PostMapping("/api/users/logout")
    public ResponseEntity<String> logOut(HttpSession session) {
        userService.logOut(session);
        return ResponseEntity.ok("Logged out successfully");
    }

    @CrossOrigin(origins = "http://localhost:4200") // Allow frontend access
    @PostMapping("/api/users/check")
    public ResponseEntity<UserResponse> checkLoggedIn(HttpSession session) {
        UserResponse userResponse = userService.checkIfLoggedIn(session);

        if (userResponse != null) {
            return ResponseEntity.ok(userResponse); 
        }

        return ResponseEntity.badRequest().body(null);
    }
}