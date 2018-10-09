package tech.simter.start.springdatajpa.repository;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tech.simter.start.springdatajpa.dto.CodeName;
import tech.simter.start.springdatajpa.dto.CodeNameInterface;
import tech.simter.start.springdatajpa.po.Entity1;
import tech.simter.start.springdatajpa.po.Entity1.Status;

import java.util.List;
import java.util.Optional;

/**
 * @author RJ
 */
public interface Entity1JpaRepository extends JpaRepository<Entity1, Integer>, Entity1ContrastMethods {
  /**
   * Note: make sure the query has a single or empty result.
   * <p>
   * If the query result is empty (found nothing), return {@link Optional#EMPTY}.
   */
  @Query("select code from Entity1 where id = :id")
  Optional<String> getCodeById(@Param("id") Integer id);

  /**
   * Note: make sure the query has a single or empty result.
   * <p>
   * If the query result is empty (found nothing), the return value is null.
   */
  @Query("select code from Entity1 where id = :id")
  String getCodeById2(@Param("id") Integer id);

  /**
   * Note: if entity exists and its code value is null, throw NPE.
   */
  default Optional<String> getMaxCode(String codePrefix) {
    return findAllCodeStartingWith(codePrefix, PageRequest.of(0, 1, Sort.by(Sort.Direction.DESC, "code")))
      .stream().findFirst();
  }

  /**
   * This method is only for {@link #getMaxCode(String)}
   * Use {@link Pageable} to set the limit and order by.
   *
   * @return the query result or empty list if found nothing
   */
  @Query(value = "select code from Entity1 where code like ?1%")
  List<String> findAllCodeStartingWith(String codePrefix, Pageable pageable);

  // test interface-based projection
  List<CodeNameInterface> findByCodeStartingWithOrderByCodeAsc(String codePrefix);

  Optional<CodeNameInterface> findByCode(String code);

  // test calss-based projection
  List<CodeName> findByCodeStartingWithOrderByCodeDesc(String codePrefix);

  Optional<CodeName> getByCode(String code);

  Optional<Entity1> findByName(String name);

  List<CodeNameInterface> findByCodeIn(List<String> codes);

  @Modifying(clearAutomatically = true)
  @Query("update #{#entityName} set status = :status where id = :id")
  int updateStatusById(@Param("id") Integer id, @Param("status") Status status);

  /**
   * For test default method
   */
  default int updateStatusToDone(Integer value) {
    return updateStatusById(value, Status.Done);
  }

  @Query("select status from #{#entityName} where id = :id")
  Optional<Status> getStatusById(@Param("id") Integer id);
}