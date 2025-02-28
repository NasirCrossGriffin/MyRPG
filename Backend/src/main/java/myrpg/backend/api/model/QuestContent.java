package myrpg.backend.api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import myrpg.backend.api.dto.QuestContentResponse;


@Entity
@Table(
    name = "QuestContent"
)
public class QuestContent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content_url", nullable = false, unique = false)
    private String contentUrl;

    @Column(name = "type", nullable = false, unique = false)
    private String type;
    
    @ManyToOne
    @JoinColumn(name = "quest_id")
    private Quest quest;

    public QuestContent() {

    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContentUrl() {
        return this.contentUrl;
    }

    public void setContentUrl(String contentUrl) {
        this.contentUrl = contentUrl;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Quest getQuest() {
        return this.quest;
    }

    public void setQuest(Quest quest) {
        this.quest = quest;
    }

    public QuestContentResponse createResponse() {
        QuestContentResponse questContentResponse = new QuestContentResponse();
        questContentResponse.setId(this.id);
        questContentResponse.setContentUrl(this.contentUrl);
        questContentResponse.setType(this.type);
        questContentResponse.setQuestId(this.quest.getId());
        return questContentResponse;
    }
}
