package myrpg.backend.service;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import myrpg.backend.api.model.User;
import myrpg.backend.api.repository.UserRepository;
import myrpg.backend.api.dto.AuthenticationRequest;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUser(Long id) {
        System.out.println("Get user route Accessed.");
        Optional<User> user = userRepository.findById(id);
        return user.orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
    }
    
    public User createUser(User user) {
        System.out.println("Create user route Accessed.");
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = user.getPassword();
        String hashedPassword = passwordEncoder.encode(password);
        user.setPassword(hashedPassword);
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        System.out.println("Delete user route Accessed.");
        userRepository.deleteById(id);
    }

    public ResponseEntity<String> authenticate(AuthenticationRequest authenticationRequest) {
        System.out.println("Authenticate user route Accessed.");
        String username = authenticationRequest.getUsername();
        String password = authenticationRequest.getPassword();
        Optional<User> optionalUser = userRepository.findUserByUsername(username);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();      
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String hashedPassword = user.getPassword(); 
            boolean isMatch = passwordEncoder.matches(password, hashedPassword);
            if (isMatch == true) {
                return ResponseEntity.ok("User Authenticated");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Wrong password");
            }  
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }
}
