package tech.simter.start.springdatajpa.repository.entity1;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import tech.simter.start.springdatajpa.repository.Entity1JpaRepository;
import tech.simter.start.springdatajpa.UnitTestConfiguration;
import tech.simter.start.springdatajpa.po.Entity1;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

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
    assertFalse(repository.getMaxCode(codePrefix).isPresent());

    // with some data
    Entity1 entity1 = new Entity1();
    entity1.setCode("_" + codePrefix);
    repository.saveAndFlush(entity1);
    assertFalse(repository.getMaxCode(codePrefix).isPresent());
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
    Optional<String> maxCodeOptional = repository.getMaxCode(codePrefix);
    assertTrue(maxCodeOptional.isPresent());
    assertEquals(codePrefix + max, maxCodeOptional.get());
  }
}