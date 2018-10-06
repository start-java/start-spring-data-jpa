package tech.simter.start.springdatajpa.repository.entity1;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import tech.simter.start.springdatajpa.repository.Entity1JpaRepository;
import tech.simter.start.springdatajpa.UnitTestConfiguration;
import tech.simter.start.springdatajpa.po.Entity1;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author RJ
 */
@SpringJUnitConfig(UnitTestConfiguration.class)
@DataJpaTest
class GetCodeByIdMethodTest {
  private Entity1JpaRepository repository;

  @Autowired
  GetCodeByIdMethodTest(Entity1JpaRepository repository) {
    this.repository = repository;
  }

  private String codePrefix = "test";

  @Test
  void notFound() {
    assertFalse(repository.getCodeById(99).isPresent());
  }

  @Test
  void found() {
    // init data
    String code = "test";
    Entity1 entity1 = new Entity1();
    entity1.setCode(code);
    repository.saveAndFlush(entity1);

    // verify
    Optional<String> code2 = repository.getCodeById(entity1.getId());
    assertTrue(code2.isPresent());
    assertEquals(code, code2.get());
  }
}