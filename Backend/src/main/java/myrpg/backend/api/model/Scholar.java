package myrpg.backend.api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
public class Scholar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment
    private Long id;
    
    private String charName;
    private int level;
    private int toNextLevel;
    private int math;
    private int language;
    private int history;
    private int science;

    public String getCharName() {
    return charName;
    }

    public void setCharName(String charName) {
        this.charName = charName;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getToNextLevel() {
        return toNextLevel;
    }

    public void setToNextLevel(int toNextLevel) {
        this.toNextLevel = toNextLevel;
    }

    public int getMath() {
        return math;
    }

    public void setMath(int math) {
        this.math = math;
    }

    public int getLanguage() {
        return language;
    }

    public void setLanguage(int language) {
        this.language = language;
    }

    public int getHistory() {
        return history;
    }

    public void setHistory(int history) {
        this.history = history;
    }

    public int getScience() {
        return science;
    }

    public void setScience(int science) {
        this.science = science;
    }
}