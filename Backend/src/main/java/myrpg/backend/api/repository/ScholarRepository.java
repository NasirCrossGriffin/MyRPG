package myrpg.backend.api.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import myrpg.backend.api.model.Scholar;

public interface ScholarRepository extends JpaRepository<Scholar, Long> {
}