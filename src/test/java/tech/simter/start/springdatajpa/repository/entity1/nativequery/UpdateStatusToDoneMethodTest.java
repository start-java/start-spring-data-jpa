package tech.simter.start.springdatajpa.repository.entity1.nativequery;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import tech.simter.start.springdatajpa.UnitTestConfiguration;
import tech.simter.start.springdatajpa.po.Entity1;
import tech.simter.start.springdatajpa.po.Entity1.Status;
import tech.simter.start.springdatajpa.repository.Entity1JpaRepositoryWithNativeQuery;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author RJ
 */
@SpringJUnitConfig(UnitTestConfiguration.class)
@DataJpaTest
class UpdateStatusToDoneMethodTest {
  private Entity1JpaRepositoryWithNativeQuery repository;
  private EntityManager entityManager;

  @Autowired
  UpdateStatusToDoneMethodTest(Entity1JpaRepositoryWithNativeQuery repository, EntityManager entityManager) {
    this.repository = repository;
    this.entityManager = entityManager;
  }

  @Test
  void test() {
    // init data
    Entity1 e = new Entity1();
    e.setCode("c");
    e.setStatusOrdinal(Status.Todo);
    e.setStatusName(Status.Todo);
    e.setStatus(Status.Todo);
    repository.save(e);

    // invoke
    assertEquals(1, repository.updateStatusToDone(e.getId()));

    // verify
    assertEquals(Status.Done, entityManager
      .createQuery("select status from Entity1 where id = :id", Status.class)
      .setParameter("id", e.getId())
      .getSingleResult()
    );
  }
}