package tech.simter.start.springdatajpa.repository.entity1;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import tech.simter.start.springdatajpa.UnitTestConfiguration;
import tech.simter.start.springdatajpa.po.Entity1;
import tech.simter.start.springdatajpa.repository.Entity1JpaRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author RJ
 */
@SpringJUnitConfig(UnitTestConfiguration.class)
@DataJpaTest
class CustomRepositoryTest {
  private Entity1JpaRepository repository;

  @Autowired
  CustomRepositoryTest(Entity1JpaRepository repository) {
    this.repository = repository;
  }

  @Test
  void notFound() {
    assertTrue(repository.findAllByName("UNKNOWN").isEmpty());
  }

  @Test
  void found() {
    // init data
    String name = "test";
    Entity1 entity1 = new Entity1();
    entity1.setCode(name);
    entity1.setName(name);
    repository.saveAndFlush(entity1);

    // verify
    List<Entity1> r = repository.findAllByName(name);
    assertEquals(1, r.size());
    assertEquals(entity1, r.get(0));
  }
}