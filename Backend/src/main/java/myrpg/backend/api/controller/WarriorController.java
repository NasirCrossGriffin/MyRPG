package myrpg.backend.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import myrpg.backend.api.model.Warrior;
import myrpg.backend.service.WarriorService;



@RestController
public class WarriorController {

    private WarriorService warriorService;

    @Autowired
    public WarriorController(WarriorService warriorService) {
        this.warriorService = warriorService;
    }

    @CrossOrigin(origins = "http://localhost:4200") // Allow frontend access
    @GetMapping("/api/warrior/{id}")
    public Warrior getWarrior(@PathVariable Long id) {
        System.out.println(id);
    	return warriorService.getWarrior(id);
    }
    
    @CrossOrigin(origins = "http://localhost:4200") // Allow frontend access
    @PostMapping("/api/warrior")
    public Warrior createWarrior(@RequestBody Warrior warrior) {
        return warriorService.createWarrior(warrior);
    }

    @CrossOrigin(origins = "http://localhost:4200") // Allow frontend access
    @DeleteMapping("/api/warrior/{id}")
    public void deleteWarrior(@PathVariable Long id) {
    	warriorService.deleteWarrior(id);
    }
}