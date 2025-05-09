package myrpg.backend.api.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import myrpg.backend.api.model.QuestContent;

public interface QuestContentRepository extends JpaRepository<QuestContent, Long> {
    @Query("SELECT q FROM QuestContent q WHERE q.quest.id = :questId")
    List<QuestContent> findQuestContentByQuestId(@Param("questId") Long questId);
}

