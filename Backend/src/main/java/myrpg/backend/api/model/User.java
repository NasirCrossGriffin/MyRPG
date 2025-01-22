package myrpg.backend.api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment
    private Long id;
    
    private String username;
    private String password;
    private String email;
    private String className;
    private Long classId;
    private String subClassName;
    private Long subClassId;
    
    public User() {
    }

    public User(String username, String password, String email, String className, Long classId, String subClassName, Long subClassId) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.className = className;
        this.classId = classId;
        this.subClassName = subClassName;
        this.subClassId = subClassId;
    }
    
    public long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getClassName() {
        return this.className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public long getClassId() {
        return this.classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public String getSubClassName() {
        return this.subClassName;
    }

    public void setSubClassName(String subClassName) {
        this.subClassName = subClassName;
    }

    public Long getSubClassId() {
        return this.subClassId;
    }

    public void setSubClassId(Long subClassId) {
        this.subClassId = subClassId;
    }
}