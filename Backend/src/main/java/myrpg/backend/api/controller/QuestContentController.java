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

import myrpg.backend.api.dto.QuestContentRequest;
import myrpg.backend.api.dto.QuestContentResponse;
import myrpg.backend.service.QuestContentService;



@RestController
public class QuestContentController {

    private QuestContentService questContentService;

    @Autowired
    public QuestContentController(QuestContentService questContentService) {
        this.questContentService = questContentService;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/api/questcontent")
    public List<QuestContentResponse> getQuestContent() {
        return questContentService.getQuestContent();
    }

    @CrossOrigin(origins = "http://localhost:4200") // Allow frontend access
    @GetMapping("/api/questcontent/{id}")
    public QuestContentResponse getQuestContent(@PathVariable Long id) {
        System.out.println(id);
    	return questContentService.getQuestContent(id);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/api/questcontent/quest/{questId}")
    public List<QuestContentResponse> getQuestContentByQuestId(@PathVariable Long questId) {
        return questContentService.getQuestContentByQuestId(questId);
    }

    @CrossOrigin(origins = "http://localhost:4200") // Allow frontend access
    @PostMapping("/api/questcontent")
    public QuestContentResponse createQuestContent(@RequestBody QuestContentRequest request) {
        return questContentService.createQuestContent(request);
    }

    @CrossOrigin(origins = "http://localhost:4200") // Allow frontend access
    @DeleteMapping("/api/questcontent/{id}")
    public void deleteQuestContent(@PathVariable Long id) {
    	questContentService.deleteQuestContent(id);
    }
}