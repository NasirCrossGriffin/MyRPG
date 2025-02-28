package myrpg.backend.api.dto;

import java.util.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class QuestContentRequest {
    private String contentUrl;
    private String type;
    private Long questId;

    public QuestContentRequest() {

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
    
    public Long getQuestId() {
        return this.questId;
    }

    public void setQuestId(Long questId) {
        this.questId = questId;
    }
}

