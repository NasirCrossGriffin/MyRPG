package myrpg.backend.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import myrpg.backend.api.dto.QuestContentRequest;
import myrpg.backend.api.dto.QuestContentResponse;
import myrpg.backend.api.model.Quest;
import myrpg.backend.api.model.QuestContent;
import myrpg.backend.api.repository.QuestContentRepository;
import myrpg.backend.api.repository.QuestRepository;

@Service
public class QuestContentService {

    private final QuestContentRepository questContentRepository;

    private final QuestRepository questRepository;

    public QuestContentService(QuestRepository questRepository, QuestContentRepository questContentRepository) {
        this.questRepository = questRepository;
        this.questContentRepository = questContentRepository;
    }

    public List<QuestContentResponse> getQuestContent() {
        System.out.println("Get all quest content route Accessed.");
        List<QuestContent> questContent = questContentRepository.findAll();

        List<QuestContentResponse> questContentResponses = new ArrayList<>();

        /* 
        for (int i = 0; i < questContent.size(); i++) {
            questContentResponses.add(questContent.get(i).createResponse());
        } 
        */

        questContent.forEach(questContentIter -> questContentResponses.add(questContentIter.createResponse()));

        return questContentResponses;
    }

    public QuestContentResponse getQuestContent(Long id) {
        System.out.println("Get Quest by id route Accessed.");
        Optional<QuestContent> questContent = questContentRepository.findById(id);
        QuestContent retrievedQuestContent = questContent.orElseThrow(() -> new RuntimeException("Quest not found with ID: " + id));
        return retrievedQuestContent.createResponse();
    }
    
    public QuestContentResponse createQuestContent(QuestContentRequest request) {
        Quest quest = questRepository.findById(request.getQuestId())
        .orElseThrow(() -> new RuntimeException("Quest not found with ID: " + request.getQuestId()));

        QuestContent newQuestContent = new QuestContent();
        newQuestContent.setContentUrl(request.getContentUrl());
        newQuestContent.setType(request.getType());
        newQuestContent.setQuest(quest);

        questContentRepository.save(newQuestContent);

        return newQuestContent.createResponse();
    }

    public List<QuestContentResponse> getQuestContentByQuestId(Long questId) {
        List<QuestContent> questContent = questContentRepository.findQuestContentByQuestId(questId);

        List<QuestContentResponse> questContentResponses = new ArrayList<>();

        /* 
        for (int i = 0; i < questContent.size(); i++) {
            questContentResponses.add(questContent.get(i).createResponse());
        } 
        */

        questContent.forEach(questContentIter -> questContentResponses.add(questContentIter.createResponse()));

        return questContentResponses;        
    }

    public void deleteQuestContent(Long id) {
        System.out.println("Delete user route Accessed.");
        questContentRepository.deleteById(id);
    }
}
