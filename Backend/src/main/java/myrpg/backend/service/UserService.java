package myrpg.backend.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;
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

    private final SessionService sessionService;

    public UserService(UserRepository userRepository, ClassRepository classRepository, SessionService sessionService) {
        this.userRepository = userRepository;
        this.classRepository = classRepository;
        this.sessionService = sessionService;
    }

    public List<UserResponse> getUser() {
        System.out.println("Get all users route Accessed.");
        List<User> users = userRepository.findAll();

        List<UserResponse> userResponses = new ArrayList<>();
        
        /* 
        for (int i = 0; i < users.size(); i++) {
            userResponses.add(users.get(i).createResponse());
        } 
        */

        users.forEach(user -> userResponses.add(user.createResponse()));

        return userResponses;
    }

    public UserResponse getUser(Long id) {
        System.out.println("Get user by id route Accessed.");
        Optional<User> user = userRepository.findById(id);
        User retrievedUser = user.orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
        return retrievedUser.createResponse();
    }
    
    public UserResponse createUser(UserRequest request, HttpSession session) {
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


        this.sessionService.createSession(session, userEntity);

        return userEntity.createResponse();
    }

    public UserResponse updateUser(UserResponse request) {
        Optional<User> userRef = userRepository.findById(request.getId());
        User user = userRef.orElseThrow(() -> new RuntimeException("User not found with ID: " + request.getId()));
        
        Class characterclass = classRepository.findById(request.getClassId())
        .orElseThrow(() -> new RuntimeException("Class not found with ID: " + request.getClassId()));

        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setLevel(request.getLevel());
        user.setToNextLevel(request.getToNextLevel());
        user.setCharacterclass(characterclass);
        user.setProfilePic(request.getProfilePic());
        user.setBannerPic(request.getBannerPic());
        user.setPassword(request.getPassword());
        
        System.out.println("Create user route Accessed.");
        
        
        User userEntity = userRepository.save(user);

        return userEntity.createResponse();
    }

    public void deleteUser(Long id) {
        System.out.println("Delete user route Accessed.");
        userRepository.deleteById(id);
    }

    public UserResponse authenticate(AuthenticationRequest authenticationRequest, HttpSession session) {
        System.out.println("Authenticate user route Accessed.");
        String username = authenticationRequest.getUsername();
        String password = authenticationRequest.getPassword();
        System.out.println(username);
        System.out.println(password);
        Optional<User> optionalUser = userRepository.findUserByUsername(username);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            System.out.println(user);      
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String hashedPassword = user.getPassword(); 
            System.out.println(user.getPassword());
            boolean isMatch = passwordEncoder.matches(password, hashedPassword);
            System.out.println(isMatch); 
            if (isMatch == true) {
                System.out.println("There's a match");
                this.sessionService.createSession(session, user);
                return user.createResponse();
            } else {
                return null;
            }  
        } else {
            return null;
        }
    }

    public UserResponse checkIfLoggedIn(HttpSession session) {
        System.out.println("Check if logged in route accessed.");
        UserResponse userResponse = this.sessionService.getSession(session);
        System.out.println(userResponse);

        if (userResponse != null) {
            Optional<User> user = userRepository.findById(userResponse.getId());
            User retrievedUser = user.orElseThrow(() -> new RuntimeException("User not found with ID: " + userResponse.getId()));
            return retrievedUser.createResponse();
        }

        return null;
    }

    public void logOut(HttpSession session) {
        this.sessionService.invalidateSession(session);
    }
}
