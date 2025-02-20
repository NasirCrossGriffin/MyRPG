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

import myrpg.backend.api.dto.StatRequest;
import myrpg.backend.api.dto.StatResponse;
import myrpg.backend.service.StatService;



@RestController
public class StatController {

    private StatService statService;

    @Autowired
    public StatController(StatService statService) {
        this.statService = statService;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/api/stat")
    public List<StatResponse> getStat() {
        return statService.getStat();
    }

    @CrossOrigin(origins = "http://localhost:4200") // Allow frontend access
    @GetMapping("/api/stat/{id}")
    public StatResponse getStat(@PathVariable Long id) {
        System.out.println(id);
    	return statService.getStat(id);
    }
   
    @CrossOrigin(origins = "http://localhost:4200") // Allow frontend access
    @PostMapping("/api/stat")
    public StatResponse createStat(@RequestBody StatRequest request) {
        return statService.createStat(request);
    }

    @CrossOrigin(origins = "http://localhost:4200") // Allow frontend access
    @DeleteMapping("/api/stat/{id}")
    public void deleteStat(@PathVariable Long id) {
    	statService.deleteStat(id);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/api/stat/class/{classId}")
    public List<StatResponse> getStatsByClassId(@PathVariable Long classId) {
        return statService.getStatsByClassId(classId);
    }
}