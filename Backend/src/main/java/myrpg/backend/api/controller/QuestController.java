package myrpg.backend.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import myrpg.backend.api.dto.QuestRequest;
import myrpg.backend.api.dto.QuestResponse;
import myrpg.backend.service.QuestService;



@RestController
public class QuestController {

    private QuestService questService;

    @Autowired
    public QuestController(QuestService questService) {
        this.questService = questService;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/api/quest")
    public List<QuestResponse> getQuest() {
        return questService.getQuest();
    }

    @CrossOrigin(origins = "http://localhost:4200") // Allow frontend access
    @GetMapping("/api/quest/{id}")
    public QuestResponse getQuest(@PathVariable Long id) {
        System.out.println(id);
    	return questService.getQuest(id);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/api/quest/user/{userId}")
    public List<QuestResponse> getQuestByUserId(@PathVariable Long userId) {
        return questService.getQuestByUserId(userId);
    }

   
    @CrossOrigin(origins = "http://localhost:4200") // Allow frontend access
    @PostMapping("/api/quest")
    public QuestResponse createQuest(@RequestBody QuestRequest request) {
        return questService.createQuest(request);
    }

    @CrossOrigin(origins = "http://localhost:4200") // Allow frontend access
    @DeleteMapping("/api/quest/{id}")
    public void deleteQuest(@PathVariable Long id) {
    	questService.deleteQuest(id);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/api/quest/follow/{userId}")
    public ResponseEntity<List<QuestResponse>> getFollowedUserQuests(@PathVariable Long userId) {
        List<QuestResponse> questResponses = questService.getFollowedUserQuest(userId);

        if (questResponses != null) {
            return ResponseEntity.ok(questResponses);
        }

        return ResponseEntity.internalServerError().body(null);
    }
}