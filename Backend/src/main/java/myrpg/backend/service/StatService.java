package myrpg.backend.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import myrpg.backend.api.dto.StatRequest;
import myrpg.backend.api.dto.StatResponse;
import myrpg.backend.api.model.Class;
import myrpg.backend.api.model.Stat;
import myrpg.backend.api.repository.ClassRepository;
import myrpg.backend.api.repository.StatRepository;

@Service
public class StatService {

    private final StatRepository statRepository;

    private final ClassRepository classRepository;

    public StatService(StatRepository statRepository, ClassRepository classRepository) {
        this.statRepository = statRepository;
        this.classRepository = classRepository;
    }

    public List<StatResponse> getStat() {
        System.out.println("Get all stats route Accessed.");
        List<Stat> stats = statRepository.findAll();
        
        List<StatResponse> statResponses = new ArrayList<>();

        /* 
        for (int i = 0; i < stats.size(); i++) {
            statResponses.add(stats.get(i).createResponse());
        }
        */
        
        stats.forEach(stat -> statResponses.add(stat.createResponse()));


        return statResponses;
    }

    public StatResponse getStat(Long id) {
        System.out.println("Get stat by id route Accessed.");
        Optional<Stat> stat = statRepository.findById(id);
        Stat retrievedStat = stat.orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
        return retrievedStat.createResponse();
    }

    public List<StatResponse> getStatsByClassId(Long classId) {
        System.out.println("Get stats by classId route Accessed.");
        List<Stat> stats = statRepository.findStatsByClassId(classId);
        List<StatResponse> statResponses = new ArrayList<>();

        /* 
        for (int i = 0; i < stats.size(); i++) {
            statResponses.add(stats.get(i).createResponse());
        } 
        */

        stats.forEach(stat -> statResponses.add(stat.createResponse()));

        return statResponses;
    }
    
    public StatResponse createStat(StatRequest request) {
        Class characterclass = classRepository.findById(request.getClassId())
        .orElseThrow(() -> new RuntimeException("Class not found"));

        Stat newStat = new Stat();

        newStat.setName(request.getName());
        newStat.setValue(request.getValue());
        newStat.setClassObj(characterclass);

        System.out.println("Create stat route Accessed.");
        Stat statEntity = statRepository.save(newStat);
        
        return statEntity.createResponse();
    }

    public StatResponse updateStat(StatResponse request) {
        Class characterclass = classRepository.findById(request.getClassId())
        .orElseThrow(() -> new RuntimeException("Class not found"));

        Optional<Stat> stat = statRepository.findById(request.getId());
        Stat retrievedStat = stat.orElseThrow(() -> new RuntimeException("Stat not found with ID: " + request.getId()));

        retrievedStat.setName(request.getName());
        retrievedStat.setValue(request.getValue());
        retrievedStat.setClassObj(characterclass);

        System.out.println("Create stat route Accessed.");
        Stat statEntity = statRepository.save(retrievedStat);
        
        return statEntity.createResponse();
    }

    public void deleteStat(Long id) {
        System.out.println("Delete stat route Accessed.");
        statRepository.deleteById(id);
    }
}
