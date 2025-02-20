package myrpg.backend.api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import myrpg.backend.api.dto.StatResponse;

@Entity
@Table(
    name = "Stat"
)



public class Stat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = false)
    private String name;

    @Column(name = "value", nullable = false, unique = false)
    private int value;

    @ManyToOne
    @JoinColumn(name = "class_id", nullable = false, unique = false)
    private Class classObj;

    public Stat() {

    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return this.value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Class getClassObj() {
        return this.classObj;
    }

    public void setClassObj(Class classObj) {
        this.classObj = classObj;
    }

    public StatResponse createResponse() {
        StatResponse statResponse = new StatResponse();
        statResponse.setId(this.getId());
        statResponse.setName(this.getName());
        statResponse.setValue(this.getValue());
        statResponse.setClassId(this.getClassObj().getId());
        return statResponse;
    }
}
