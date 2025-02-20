package myrpg.backend.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
    public UserResponse createUser(@RequestBody UserRequest request) {
        return userService.createUser(request);
    }

    @CrossOrigin(origins = "http://localhost:4200") // Allow frontend access
    @DeleteMapping("/api/users/{id}")
    public void deleteUser(@PathVariable Long id) {
    	userService.deleteUser(id);
    }
    
    @CrossOrigin(origins = "http://localhost:4200") // Allow frontend access
    @PostMapping("/api/users/authenticate")
    public ResponseEntity<String> authenticateUser(@RequestBody AuthenticationRequest authenticationRequest) {
        return userService.authenticate(authenticationRequest);
    }
}