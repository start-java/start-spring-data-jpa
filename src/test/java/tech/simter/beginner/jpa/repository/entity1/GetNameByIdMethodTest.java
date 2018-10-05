package tech.simter.beginner.jpa.repository.entity1;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import tech.simter.beginner.jpa.UnitTestConfiguration;
import tech.simter.beginner.jpa.po.Entity1;
import tech.simter.beginner.jpa.repository.Entity1JpaRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * @author RJ
 */
@SpringJUnitConfig(UnitTestConfiguration.class)
@DataJpaTest
class GetNameByIdMethodTest {
  private Entity1JpaRepository repository;

  @Autowired
  GetNameByIdMethodTest(Entity1JpaRepository repository) {
    this.repository = repository;
  }

  @Test
  void foundNothing() {
    assertNull(repository.getNameById(99));
  }

  @Test
  void foundIt() {
    // init data
    Entity1 entity1 = new Entity1();
    entity1.setName("test");
    repository.saveAndFlush(entity1);

    // verify
    assertEquals("test", repository.getNameById(entity1.getId()));
  }
}