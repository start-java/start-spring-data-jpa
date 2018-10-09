package tech.simter.start.springdatajpa.repository.entity1.nativequery;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import tech.simter.start.springdatajpa.UnitTestConfiguration;
import tech.simter.start.springdatajpa.po.Entity1;
import tech.simter.start.springdatajpa.po.Entity1.Status;
import tech.simter.start.springdatajpa.repository.Entity1JpaRepositoryWithNativeQuery;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author RJ
 */
@SpringJUnitConfig(UnitTestConfiguration.class)
@DataJpaTest
class GetStatusByIdMethodTest {
  private Entity1JpaRepositoryWithNativeQuery repository;

  @Autowired
  GetStatusByIdMethodTest(Entity1JpaRepositoryWithNativeQuery repository) {
    this.repository = repository;
  }

  @Test
  void test() {
    // init data
    Entity1 e = new Entity1();
    e.setCode("c");
    e.setStatusOrdinal(Status.Todo);
    e.setStatusName(Status.Todo);
    e.setStatus(Status.Todo);
    repository.save(e);

    // invoke
    Optional<Integer> status = repository.getStatusById(e.getId());

    // verify
    assertTrue(status.isPresent());
    assertEquals(Status.Todo.value(), status.get());
  }
}