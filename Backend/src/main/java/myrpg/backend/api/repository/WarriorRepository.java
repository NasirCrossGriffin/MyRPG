package myrpg.backend.api.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import myrpg.backend.api.model.Warrior;

public interface WarriorRepository extends JpaRepository<Warrior, Long> {
}