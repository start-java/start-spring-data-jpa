package tech.simter.start.springdatajpa.repository;

import org.springframework.stereotype.Repository;
import tech.simter.start.springdatajpa.po.Entity1;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author RJ
 */
@Repository
public class Entity1CustomRepositoryImpl implements Entity1CustomRepository {
  @PersistenceContext
  private EntityManager em;

  @Override
  public List<Entity1> findAllByName(String name) {
    return em.createQuery("select e from Entity1 e where name = :name", Entity1.class)
      .setParameter("name", name)
      .getResultList();
  }
}