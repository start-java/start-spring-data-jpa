package tech.simter.beginner.jpa.repository.entity1;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import tech.simter.beginner.jpa.UnitTestConfiguration;
import tech.simter.beginner.jpa.po.Entity1;
import tech.simter.beginner.jpa.repository.Entity1JpaRepository;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * @author RJ
 */
@SpringJUnitConfig(UnitTestConfiguration.class)
@DataJpaTest
class GetMaxCodeMethodTest {
  private Entity1JpaRepository repository;

  @Autowired
  GetMaxCodeMethodTest(Entity1JpaRepository repository) {
    this.repository = repository;
  }

  private String codePrefix = "test";

  @Test
  void notFound() {
    // without data
    assertNull(repository.getMaxCode(codePrefix));

    // with some data
    Entity1 entity1 = new Entity1();
    entity1.setName("_" + codePrefix);
    repository.saveAndFlush(entity1);
    assertNull(repository.getMaxCode(codePrefix));
  }

  @Test
  void found() {
    // init data
    int max = 3;
    repository.saveAll(
      IntStream.range(1, max + 1).boxed().map(t -> {
        Entity1 entity1 = new Entity1();
        entity1.setCode(codePrefix + t);
        return entity1;
      }).collect(Collectors.toList())
    );

    // verify
    assertEquals(codePrefix + max, repository.getMaxCode(codePrefix));
  }
}