package myrpg.backend.api.model;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import myrpg.backend.api.dto.QuestResponse;

@Entity
@Table(
    name = "Quest"
)
public class Quest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "name", nullable = false, unique = false)
    private String name;

    @Column(name = "datetime", nullable = false, unique = false)
    private String datetime;

    @Column(name = "description", nullable = false, unique = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User uploader;

    @OneToMany(mappedBy = "quest")
    private Set<QuestContent> questContent = new HashSet<>();

    // Default constructor
    public Quest() {

    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUploader() {
        return this.uploader;
    }

    public void setUploader(User uploader) {
        this.uploader = uploader;
    }

    public Set<QuestContent> getQuestContent() {
        return this.questContent;
    }

    public void setQuestContent(Set<QuestContent> questContent) {
        this.questContent = questContent;
    }

    public QuestResponse createResponse() {
        QuestResponse questResponse = new QuestResponse();
        questResponse.setId(this.id);
        questResponse.setName(this.name);
        questResponse.setDatetime(this.datetime);
        questResponse.setDescription(this.description);
        questResponse.setUserId(this.getUploader().getId());
        return questResponse;
    }
}
