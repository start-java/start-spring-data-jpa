package tech.simter.beginner.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tech.simter.beginner.jpa.po.Entity1;

/**
 * @author RJ
 */
public interface Entity1JpaRepository extends JpaRepository<Entity1, Integer> {
  @Query("select name from Entity1 where id = :id")
  String getNameById(@Param("id") Integer id);
}