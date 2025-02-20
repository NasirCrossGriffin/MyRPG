package myrpg.backend.api.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import myrpg.backend.api.model.Stat;

public interface StatRepository extends JpaRepository<Stat, Long> {
    @Query("SELECT s FROM Stat s WHERE s.classObj.id = :classId")
    List<Stat> findStatsByClassId(@Param("classId") Long classId);
}