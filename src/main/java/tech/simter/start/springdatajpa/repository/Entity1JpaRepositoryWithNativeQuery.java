package tech.simter.start.springdatajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tech.simter.start.springdatajpa.dto.CodeNameInterface;
import tech.simter.start.springdatajpa.po.Entity1;
import tech.simter.start.springdatajpa.po.Entity1.Status;

import java.util.List;
import java.util.Optional;

/**
 * @author RJ
 */
public interface Entity1JpaRepositoryWithNativeQuery extends JpaRepository<Entity1, Integer>, Entity1ContrastMethods {
  /**
   * Use max aggregate function to get the max code.
   * <p>
   * If the query result is empty (found nothing), return {@link Optional#EMPTY}.
   */
  @Query(value = "select max(code) from entity1 where code like ?1%", nativeQuery = true)
  Optional<String> getMaxCode(String codePrefix);

  @Query(value = "select code, name from entity1 where code in (:codes)", nativeQuery = true)
  List<CodeNameInterface> findByCodeIn(@Param("codes") List<String> codes);

  @Modifying(clearAutomatically = true)
  @Query(value = "update entity1 set status = :#{#status.value()} where id = :id", nativeQuery = true)
  int updateStatusById(@Param("id") Integer id, @Param("status") Status status);

  default int updateStatusToDone(Integer value) {
    return updateStatusById(value, Status.Done);
  }

  /**
   * Could not query a {@link Status} enum if use native query. Only can query the {@link Status} enums value.
   */
  @Query(value = "select status from entity1 where id = :id", nativeQuery = true)
  Optional<Integer> getStatusById(@Param("id") Integer id);
}