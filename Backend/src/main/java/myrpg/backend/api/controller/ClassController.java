package myrpg.backend.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import myrpg.backend.api.dto.ClassResponse;
import myrpg.backend.api.model.Class;
import myrpg.backend.service.ClassService;



@RestController
public class ClassController {

    private ClassService classService;

    @Autowired
    public ClassController(ClassService classService) {
        this.classService = classService;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/api/class")
    public List<ClassResponse> getCharacterclass() {
        return classService.getCharacterClass();
    }

    @CrossOrigin(origins = "http://localhost:4200") // Allow frontend access
    @GetMapping("/api/class/{id}")
    public ClassResponse getCharacterClass(@PathVariable Long id) {
        System.out.println(id);
    	return classService.getCharacterClass(id);
    }
   
    @CrossOrigin(origins = "http://localhost:4200") // Allow frontend access
    @PostMapping("/api/class")
    public ClassResponse createClass(@RequestBody Class characterclass) {
        return classService.createClass(characterclass);
    }

    @CrossOrigin(origins = "http://localhost:4200") // Allow frontend access
    @DeleteMapping("/api/class/{id}")
    public void deleteClass(@PathVariable Long id) {
    	classService.deleteClass(id);
    }
}