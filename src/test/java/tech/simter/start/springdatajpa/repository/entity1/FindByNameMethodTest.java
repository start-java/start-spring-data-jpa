package tech.simter.start.springdatajpa.repository.entity1;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import tech.simter.start.springdatajpa.UnitTestConfiguration;
import tech.simter.start.springdatajpa.po.Entity1;
import tech.simter.start.springdatajpa.repository.Entity1JpaRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author RJ
 */
@SpringJUnitConfig(UnitTestConfiguration.class)
@DataJpaTest
class FindByNameMethodTest {
  private Entity1JpaRepository repository;

  @Autowired
  FindByNameMethodTest(Entity1JpaRepository repository) {
    this.repository = repository;
  }

  @Test
  void notFound() {
    assertFalse(repository.findByName("UNKNOWN").isPresent());
  }

  @Test
  void foundUnique() {
    // init data
    String name = "test";
    Entity1 entity1 = new Entity1();
    entity1.setCode(name);
    entity1.setName(name);
    repository.saveAndFlush(entity1);

    // verify
    Optional<Entity1> r = repository.findByName(name);
    assertTrue(r.isPresent());
    assertEquals(entity1.getId(), r.get().getId());
    assertEquals(name, r.get().getName());
  }

  @Test
  void failedByNonUnique() {
    // init data
    String name = "test";
    Entity1 entity1 = new Entity1();
    entity1.setCode("code1");
    entity1.setName(name);
    repository.save(entity1);
    Entity1 entity2 = new Entity1();
    entity2.setCode("code2");
    entity2.setName(name);
    repository.saveAndFlush(entity2);

    // verify
    assertThrows(IncorrectResultSizeDataAccessException.class, () -> repository.findByName(name));
  }
}