package tech.simter.beginner.jpa;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author RJ
 */
@SpringJUnitConfig(SampleTest.Cfg.class)
@DataJpaTest
class SampleTest {
  @Configuration
  @EnableJpaRepositories("tech.simter.beginner.jpa")
  @EntityScan("tech.simter.beginner.jpa")
  static class Cfg {
  }

  @Test
  public void test() {
    assertEquals(1, 1);
  }
}