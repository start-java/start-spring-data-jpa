package tech.simter.start.springdatajpa.repository.entity1;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import tech.simter.start.springdatajpa.repository.Entity1JpaRepository;
import tech.simter.start.springdatajpa.UnitTestConfiguration;
import tech.simter.start.springdatajpa.po.Entity1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * @author RJ
 */
@SpringJUnitConfig(UnitTestConfiguration.class)
@DataJpaTest
class GetCodeById2MethodTest {
  private Entity1JpaRepository repository;

  @Autowired
  GetCodeById2MethodTest(Entity1JpaRepository repository) {
    this.repository = repository;
  }

  @Test
  void notFound() {
    assertNull(repository.getCodeById2(99));
  }

  @Test
  void found() {
    // init data
    String code = "test";
    Entity1 entity1 = new Entity1();
    entity1.setCode(code);
    repository.saveAndFlush(entity1);

    // verify
    assertEquals(code, repository.getCodeById2(entity1.getId()));
  }
}