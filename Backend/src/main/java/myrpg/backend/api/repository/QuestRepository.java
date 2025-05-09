package myrpg.backend.api.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import myrpg.backend.api.model.Quest;

public interface QuestRepository extends JpaRepository<Quest, Long> {
    @Query("SELECT q FROM Quest q WHERE q.uploader.id = :userId")
    List<Quest> findQuestsByUserId(@Param("userId") Long userId);

    @Query(value = """
        WITH ACCOUNTS_USER_FOLLOWS AS (
            SELECT user_account FROM follows WHERE follower_account = :userId
        )

        SELECT q.* FROM quest q
        INNER JOIN ACCOUNTS_USER_FOLLOWS a ON q.user_id = a.user_account
        ORDER BY q.id DESC
        """, nativeQuery=true)
    List<Quest>findFollowedUserQuests(@Param("userId") Long userId);
}