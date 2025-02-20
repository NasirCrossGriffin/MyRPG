package myrpg.backend.api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import myrpg.backend.api.dto.UserResponse;


@Entity
@Table(
    name = "User",
    uniqueConstraints = { 
        @UniqueConstraint(columnNames = "username"),
        @UniqueConstraint(columnNames = "email"),
        @UniqueConstraint(columnNames = "characterclass"),
        @UniqueConstraint(columnNames = "subclass") 
    }
)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment
    private Long id;
    
    @Column(name = "username", nullable = false, unique = true)
    private String username;
    
    @Column(name = "password", nullable = false, unique = false)
    private String password;
    
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "level", nullable = false, unique = false)
    private int level;

    @Column(name = "to_next_level", nullable = false, unique = false)
    private int toNextLevel;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "class_id")
    private Class characterclass;
    
    @Column(name = "profile_pic", nullable = false, unique = false)
    private String profilePic;
    
    @Column(name = "banner_pic", nullable = false, unique = false)
    private String bannerPic;
    
    public User() {

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

    public Class getCharacterclass() {
        return this.characterclass;
    }

    public void setCharacterclass(Class characterclass) {
        this.characterclass = characterclass;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getProfilePic() {
        return this.profilePic;
    }

    public void setBannerPic(String bannerPic) {
        this.bannerPic = bannerPic;
    }

    public String getBannerPic() {
        return this.bannerPic;
    }

    public int getToNextLevel() {
        return this.toNextLevel;
    }

    public void setToNextLevel(int toNextLevel) {
        this.toNextLevel = toNextLevel;
    }

    public int getLevel() {
        return this.level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public UserResponse createResponse() {
        UserResponse userResponse = new UserResponse();
        userResponse.setId(this.getId());
        userResponse.setUsername(this.getUsername());
        userResponse.setPassword(this.getPassword());
        userResponse.setEmail(this.getEmail());
        userResponse.setLevel(this.getLevel());
        userResponse.setToNextLevel(this.getToNextLevel());
        userResponse.setClassId(this.getCharacterclass().getId());
        userResponse.setProfilePic(this.getProfilePic());
        userResponse.setBannerPic(this.getBannerPic());
        return userResponse;
    }
}