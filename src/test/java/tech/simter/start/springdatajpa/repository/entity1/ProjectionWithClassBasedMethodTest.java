package tech.simter.start.springdatajpa.repository.entity1;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import tech.simter.start.springdatajpa.repository.Entity1JpaRepository;
import tech.simter.start.springdatajpa.UnitTestConfiguration;
import tech.simter.start.springdatajpa.dto.CodeName;
import tech.simter.start.springdatajpa.po.Entity1;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author RJ
 */
@SpringJUnitConfig(UnitTestConfiguration.class)
@DataJpaTest
class ProjectionWithClassBasedMethodTest {
  private Entity1JpaRepository repository;

  @Autowired
  ProjectionWithClassBasedMethodTest(Entity1JpaRepository repository) {
    this.repository = repository;
  }

  private String codePrefix = "code";

  @Test
  void notFound() {
    assertTrue(repository.findByCodeStartingWithOrderByCodeDesc(codePrefix).isEmpty());
    assertFalse(repository.getByCode(codePrefix).isPresent());
  }

  @Test
  void found() {
    // init data
    int max = 3;
    List<Entity1> originList = repository.saveAll(
      IntStream.range(1, max + 1).boxed().map(t -> {
        Entity1 entity1 = new Entity1();
        entity1.setCode(codePrefix + t);
        entity1.setName("name" + t);
        return entity1;
      }).collect(Collectors.toList())
    );

    // invoke and verify
    List<CodeName> list = repository.findByCodeStartingWithOrderByCodeDesc(codePrefix);
    assertFalse(list.isEmpty());
    int i = 0, topIndex = originList.size() - 1;
    for (CodeName cn : list) {
      Entity1 entity1 = originList.get(topIndex - i);
      assertEquals(entity1.getCode(), cn.getCode());
      assertEquals(entity1.getName(), cn.getName());
      i++;
    }
    Optional<CodeName> one = repository.getByCode(codePrefix + max);
    assertTrue(one.isPresent());
    assertEquals(originList.get(2).getCode(), one.get().getCode());
    assertEquals(originList.get(2).getName(), one.get().getName());
  }
}