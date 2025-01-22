package myrpg.backend.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import myrpg.backend.api.model.User;
import myrpg.backend.api.dto.AuthenticationRequest;
import myrpg.backend.service.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;



@RestController
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/api/users/{id}")
    public User getUser(@PathVariable Long id) {
        System.out.println(id);
    	return userService.getUser(id);
    }
    
    @PostMapping("/api/users")
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @DeleteMapping("/api/users/{id}")
    public void deleteUser(@PathVariable Long id) {
    	userService.deleteUser(id);
    }

    @PostMapping("/api/users/authenticate")
    public ResponseEntity<String> authenticateUser(@RequestBody AuthenticationRequest authenticationRequest) {
        return userService.authenticate(authenticationRequest);
    }
}