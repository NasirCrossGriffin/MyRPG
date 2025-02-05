package myrpg.backend.service;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import myrpg.backend.api.model.Scholar;
import myrpg.backend.api.repository.ScholarRepository;

@Service
public class ScholarService {

    private final ScholarRepository scholarRepository;

    public ScholarService(ScholarRepository scholarRepository) {
        this.scholarRepository = scholarRepository;
    }

    public Scholar getScholar(Long id) {
        System.out.println("Get scholar route Accessed.");
        Optional<Scholar> scholar = scholarRepository.findById(id);
        return scholar.orElseThrow(() -> new RuntimeException("Scholar not found with ID: " + id));
    }
    
    public Scholar createScholar(Scholar scholar) {
        System.out.println("Create scholar route Accessed.");
        return scholarRepository.save(scholar);
    }

    public void deleteScholar(Long id) {
        System.out.println("Delete scholar route Accessed.");
        scholarRepository.deleteById(id);
    }
}
