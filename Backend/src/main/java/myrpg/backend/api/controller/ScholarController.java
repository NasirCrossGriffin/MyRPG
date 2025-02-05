package myrpg.backend.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import myrpg.backend.api.model.Scholar;
import myrpg.backend.service.ScholarService;



@RestController
public class ScholarController {

    private ScholarService scholarService;


    @Autowired
    public ScholarController(ScholarService scholarService) {
        this.scholarService = scholarService;
    }
    
    @CrossOrigin(origins = "http://localhost:4200") // Allow frontend access
    @GetMapping("/api/scholar/{id}")
    public Scholar getScholar(@PathVariable Long id) {
        System.out.println(id);
    	return scholarService.getScholar(id);
    }
    
    @CrossOrigin(origins = "http://localhost:4200") // Allow frontend access
    @PostMapping("/api/scholar")
    public Scholar createScholar(@RequestBody Scholar scholar) {
        return scholarService.createScholar(scholar);
    }

    @CrossOrigin(origins = "http://localhost:4200") // Allow frontend access
    @DeleteMapping("/api/scholar/{id}")
    public void deleteScholar(@PathVariable Long id) {
    	scholarService.deleteScholar(id);
    }
}