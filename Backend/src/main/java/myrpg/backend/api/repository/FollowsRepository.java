package myrpg.backend.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import myrpg.backend.api.model.Follows;

public interface FollowsRepository extends JpaRepository<Follows, Long> {

    @Query("SELECT f FROM Follows f WHERE f.followerAccount.id = :userId")
    List<Follows> getFollowingByUserId(@Param("userId") Long userId);

    @Query("SELECT f FROM Follows f WHERE f.userAccount.id = :userId")
    List<Follows> getFollowersByUserId(@Param("userId") Long userId);

    @Query(value = "SELECT COUNT(*) FROM follows WHERE follower_account = :userId", nativeQuery = true)
    int countFollowing(@Param("userId") Long userId);

    @Query(value = "SELECT COUNT(*) FROM follows WHERE user_account = :userId", nativeQuery = true)
    int countFollowers(@Param("userId") Long userId);

    @Modifying
    @Transactional
    @Query("DELETE FROM Follows f WHERE f.userAccount.id = :userId AND f.followerAccount.id = :followerId")
    void deleteFollowBridge(@Param("userId") Long userId, @Param("followerId") Long followerId);

    @Query("SELECT f FROM Follows f WHERE f.userAccount.id = :userId AND f.followerAccount.id = :followerId")
    Optional<Follows> getFollowBridge(@Param("userId") Long userId, @Param("followerId") Long followerId);
}
