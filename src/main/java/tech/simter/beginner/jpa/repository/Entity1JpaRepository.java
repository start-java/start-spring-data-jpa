package tech.simter.beginner.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tech.simter.beginner.jpa.po.Entity1;

/**
 * @author RJ
 */
public interface Entity1JpaRepository extends JpaRepository<Entity1, Integer> {
  /**
   * Note: make sure the query has a single or empty result.
   * <p>
   * If the query result is empty (found nothing), the return value is null.
   */
  @Query("select name from Entity1 where id = :id")
  String getNameById(@Param("id") Integer id);

  /**
   * Use native query for set 'limit 1' in query-sql directly.
   * <p>
   * Because jpql not support 'limit' symbol, and the 'First' or 'Top' in method name
   * only support fetch the whole entity. But need to note, this does not cross database.
   * <p>
   * If the query result is empty (found nothing), the return value is null.
   */
  @Query(
    value = "select code from entity1 where code like ?1% order by code desc limit 1",
    nativeQuery = true
  )
  String getMaxCode(String codePrefix);
}