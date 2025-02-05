package myrpg.backend.service;
import java.util.Optional;

import org.springframework.stereotype.Service;

import myrpg.backend.api.model.Warrior;
import myrpg.backend.api.repository.WarriorRepository;

@Service
public class WarriorService {

    private final WarriorRepository warriorRepository;

    public WarriorService(WarriorRepository warriorRepository) {
        this.warriorRepository = warriorRepository;
    }

    public Warrior getWarrior(Long id) {
        System.out.println("Get Warrior route Accessed.");
        Optional<Warrior> warrior = warriorRepository.findById(id);
        return warrior.orElseThrow(() -> new RuntimeException("Warrior not found with ID: " + id));
    }
    
    public Warrior createWarrior(Warrior warrior) {
        System.out.println("Create Warrior route Accessed.");
        return warriorRepository.save(warrior);
    }

    public void deleteWarrior(Long id) {
        System.out.println("Delete Warrior route Accessed.");
        warriorRepository.deleteById(id);
    }
}
