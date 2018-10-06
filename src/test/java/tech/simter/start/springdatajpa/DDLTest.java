package tech.simter.start.springdatajpa;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Debug for output the auto DDL (Data Definition Language) only.
 *
 * @author RJ
 */
@SpringJUnitConfig(UnitTestConfiguration.class)
@DataJpaTest
class DDLTest {
  @Test
  void test() {
    assertEquals(1, 1);
  }
}