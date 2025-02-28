package myrpg.backend.api.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import myrpg.backend.api.model.Quest;

public interface QuestRepository extends JpaRepository<Quest, Long> {
    @Query("SELECT q FROM Quest q WHERE q.uploader.id = :userId")
    List<Quest> findQuestsByUserId(@Param("userId") Long userId);
}