package tech.simter.beginner.jpa.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import tech.simter.beginner.jpa.po.Entity1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * @author RJ
 */
@SpringJUnitConfig(Entity1JpaRepositoryTest.Cfg.class)
@DataJpaTest
class Entity1JpaRepositoryTest {
  @Configuration
  @EnableJpaRepositories("tech.simter.beginner.jpa.repository")
  @EntityScan("tech.simter.beginner.jpa.po")
  static class Cfg {
  }

  private Entity1JpaRepository repository;

  @Autowired
  Entity1JpaRepositoryTest(Entity1JpaRepository repository) {
    this.repository = repository;
  }

  @Test
  void getNameById_NotFound() {
    assertNull(repository.getNameById(99));
  }

  @Test
  void getNameById_Found() {
    // init data
    Entity1 entity1 = new Entity1();
    entity1.setName("test");
    repository.saveAndFlush(entity1);

    // verify
    assertEquals("test", repository.getNameById(entity1.getId()));
  }
}