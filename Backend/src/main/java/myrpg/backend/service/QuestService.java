package myrpg.backend.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import myrpg.backend.api.dto.QuestRequest;
import myrpg.backend.api.dto.QuestResponse;
import myrpg.backend.api.model.Quest;
import myrpg.backend.api.model.User;
import myrpg.backend.api.repository.QuestRepository;
import myrpg.backend.api.repository.UserRepository;

@Service
public class QuestService {

    private final QuestRepository questRepository;

    private final UserRepository userRepository;

    public QuestService(UserRepository userRepository, QuestRepository questRepository) {
        this.userRepository = userRepository;
        this.questRepository = questRepository;
    }

    public List<QuestResponse> getQuest() {
        System.out.println("Get all users route Accessed.");
        List<Quest> quests = questRepository.findAll();

        List<QuestResponse> questResponses = new ArrayList<>();

        /* 
        for (int i = 0; i < quests.size(); i++) {
            questResponses.add(quests.get(i).createResponse());
        } 
        */

        quests.forEach(quest -> questResponses.add(quest.createResponse()));

        return questResponses;
    }

    public QuestResponse getQuest(Long id) {
        System.out.println("Get Quest by id route Accessed.");
        Optional<Quest> quest = questRepository.findById(id);
        Quest retrievedQuest = quest.orElseThrow(() -> new RuntimeException("Quest not found with ID: " + id));
        return retrievedQuest.createResponse();
    }

    public QuestResponse createQuest(QuestRequest request) {
        User user = userRepository.findById(request.getUserId())
        .orElseThrow(() -> new RuntimeException("User not found with ID: " + request.getUserId()));

        Quest newQuest = new Quest();
        newQuest.setName(request.getName());
        newQuest.setDescription(request.getDescription());
        newQuest.setDatetime(request.getDatetime());
        newQuest.setUploader(user);

        questRepository.save(newQuest);

        return newQuest.createResponse();
    }

    public List<QuestResponse> getQuestByUserId(Long userId) {
        List<Quest> quests = questRepository.findQuestsByUserId(userId);

        List<QuestResponse> questResponses = new ArrayList<>();

        /* 
        for (int i = 0; i < quests.size(); i++) {
            questResponses.add(quests.get(i).createResponse());
        } 
        */

        quests.forEach(quest -> questResponses.add(quest.createResponse()));

        return questResponses;        
    }

    public void deleteQuest(Long id) {
        System.out.println("Delete user route Accessed.");
        userRepository.deleteById(id);
    }
}
