package myrpg.backend.api.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import myrpg.backend.api.dto.ClassResponse;


@Entity
@Table(
    name = "Class"
)

public class Class {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name", nullable = false, unique = false) 
	private String name;
	
	@OneToMany(mappedBy = "classObj")
	private Set<Stat> stats = new HashSet<>();

	@OneToOne(mappedBy = "characterclass", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private User user;


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

	public Set<Stat> getStats() {
		return this.stats;
	}

	public void setStats(Set<Stat> stats) {
		this.stats = stats;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public ClassResponse createResponse() {
		ClassResponse classResponse = new ClassResponse();
        classResponse.setId(this.id);
		classResponse.setName(this.name);
        return classResponse;
	}
}
