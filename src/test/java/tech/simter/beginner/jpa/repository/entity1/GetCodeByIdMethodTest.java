package tech.simter.beginner.jpa.repository.entity1;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import tech.simter.beginner.jpa.UnitTestConfiguration;
import tech.simter.beginner.jpa.po.Entity1;
import tech.simter.beginner.jpa.repository.Entity1JpaRepository;

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