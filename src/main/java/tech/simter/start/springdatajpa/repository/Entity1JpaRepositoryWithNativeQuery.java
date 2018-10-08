package tech.simter.start.springdatajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tech.simter.start.springdatajpa.po.Entity1;

import java.util.Optional;

/**
 * @author RJ
 */
public interface Entity1JpaRepositoryWithNativeQuery extends JpaRepository<Entity1, Integer>, Entity1ContrastMethods {
  /**
   * Use native query for set 'limit 1' in query-sql directly to get a single result.
   * <p>
   * Because jpql not support 'limit' symbol, and the 'First' or 'Top' in method name
   * only support fetch the whole entity. But need to note, this does not cross database.
   * <p>
   * If the query result is empty (found nothing), return {@link Optional#EMPTY}.
   */
  @Query(
    value = "select code from entity1 where code like ?1% order by code desc limit 1",
    nativeQuery = true
  )
  Optional<String> getMaxCode(String codePrefix);

  @Query(value = "select code, name from #{#entityName} where code in :codes")
  List<CodeNameInterface> findByCodeIn(@Param("codes") List<String> codes);
}