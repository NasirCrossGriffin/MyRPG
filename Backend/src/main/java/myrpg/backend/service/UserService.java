package myrpg.backend.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import myrpg.backend.api.dto.AuthenticationRequest;
import myrpg.backend.api.dto.UserRequest;
import myrpg.backend.api.dto.UserResponse;
import myrpg.backend.api.model.Class;
import myrpg.backend.api.model.User;
import myrpg.backend.api.repository.ClassRepository;
import myrpg.backend.api.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final ClassRepository classRepository;

    public UserService(UserRepository userRepository, ClassRepository classRepository) {
        this.userRepository = userRepository;
        this.classRepository = classRepository;
    }

    public List<UserResponse> getUser() {
        System.out.println("Get all users route Accessed.");
        List<User> users = userRepository.findAll();

        List<UserResponse> userResponses = new ArrayList<>();

        for (int i = 0; i < users.size(); i++) {
            userResponses.add(users.get(i).createResponse());
        } 

        return userResponses;
    }

    public UserResponse getUser(Long id) {
        System.out.println("Get user by id route Accessed.");
        Optional<User> user = userRepository.findById(id);
        User retrievedUser = user.orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
        return retrievedUser.createResponse();
    }
    
    public UserResponse createUser(UserRequest request) {
        Class characterclass = classRepository.findById(request.getClassId())
        .orElseThrow(() -> new RuntimeException("Class not found with ID: " + request.getClassId()));

        User newUser = new User();
        newUser.setUsername(request.getUsername());
        newUser.setEmail(request.getEmail());
        newUser.setLevel(request.getLevel());
        newUser.setToNextLevel(request.getToNextLevel());
        newUser.setCharacterclass(characterclass);
        newUser.setProfilePic(request.getProfilePic());
        newUser.setBannerPic(request.getBannerPic());

        System.out.println("Create user route Accessed.");
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = request.getPassword();
        String hashedPassword = passwordEncoder.encode(password);
        newUser.setPassword(hashedPassword);
        
        User userEntity = userRepository.save(newUser);


        return userEntity.createResponse();
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
