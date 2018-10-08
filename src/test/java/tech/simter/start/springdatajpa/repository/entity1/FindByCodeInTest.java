package tech.simter.start.springdatajpa.repository.entity1;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import tech.simter.start.springdatajpa.UnitTestConfiguration;
import tech.simter.start.springdatajpa.repository.Entity1JpaRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author RJ
 */
@SpringJUnitConfig(UnitTestConfiguration.class)
@DataJpaTest
class FindByCodeInTest {
  private Entity1JpaRepository repository;

  @Autowired
  FindByCodeInTest(Entity1JpaRepository repository) {
    this.repository = repository;
  }

  @Test
  void notFound() {
    assertTrue(repository.findByCodeIn(Collections.singletonList("1")).isEmpty());
    assertTrue(repository.findByCodeIn(Arrays.asList("1", "2")).isEmpty());
  }

  @Test
  void emptyListParamShouldOk() {
    // will generated '...where ...code in (?)' and binding parameter [1] as [VARCHAR] - [null]
    assertTrue(repository.findByCodeIn(new ArrayList<>()).isEmpty());
  }

  @Test
  void nullListParamShouldFailed() {
    // "Value must not be null!"
    assertThrows(InvalidDataAccessApiUsageException.class, () -> repository.findByCodeIn(null));
  }
}