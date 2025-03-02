package myrpg.backend.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import myrpg.backend.api.dto.ClassResponse;
import myrpg.backend.api.model.Class;
import myrpg.backend.api.repository.ClassRepository;

@Service
public class ClassService {

    private final ClassRepository classRepository;

    public ClassService(ClassRepository classRepository) {
        this.classRepository = classRepository;
    }

    public List<ClassResponse> getCharacterClass() {
        System.out.println("Get all classes route Accessed.");
        List<Class> classes = classRepository.findAll();

        List<ClassResponse> classResponses = new ArrayList<>();

        /* 
        for (int i = 0; i < classes.size(); i++) {
            classResponses.add(classes.get(i).createResponse());
        } 
        */

        classes.forEach(thisClass -> classResponses.add(thisClass.createResponse()));

        return classResponses;
    }

    public ClassResponse getCharacterClass(Long id) {
        System.out.println("Get user by id route Accessed.");
        Optional<Class> characterclass = classRepository.findById(id);
        Class classEntity = characterclass.orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
        return classEntity.createResponse();
    }
    
    public ClassResponse createClass(Class characterclass) {
        System.out.println("Create class route Accessed.");
        Class classEntity = classRepository.save(characterclass);
        return classEntity.createResponse();
    }

    public void deleteClass(Long id) {
        System.out.println("Delete class route Accessed.");
        classRepository.deleteById(id);
    }
}
