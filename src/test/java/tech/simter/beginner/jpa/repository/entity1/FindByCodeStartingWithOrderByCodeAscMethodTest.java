package tech.simter.beginner.jpa.repository.entity1;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import tech.simter.beginner.jpa.UnitTestConfiguration;
import tech.simter.beginner.jpa.dto.CodeNameInterface;
import tech.simter.beginner.jpa.po.Entity1;
import tech.simter.beginner.jpa.repository.Entity1JpaRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author RJ
 */
@SpringJUnitConfig(UnitTestConfiguration.class)
@DataJpaTest
class FindByCodeStartingWithOrderByCodeAscMethodTest {
  private Entity1JpaRepository repository;

  @Autowired
  FindByCodeStartingWithOrderByCodeAscMethodTest(Entity1JpaRepository repository) {
    this.repository = repository;
  }

  private String codePrefix = "code";

  @Test
  void notFound() {
    assertTrue(repository.findByCodeStartingWithOrderByCodeAsc(codePrefix).isEmpty());
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

    // verify
    List<CodeNameInterface> list = repository.findByCodeStartingWithOrderByCodeAsc(codePrefix);
    assertFalse(list.isEmpty());
    int i = 0;
    for (CodeNameInterface cn : list) {
      Entity1 entity1 = originList.get(i);
      assertEquals(entity1.getCode(), cn.getCode());
      assertEquals(entity1.getName(), cn.getName());
      i++;
    }
  }
}