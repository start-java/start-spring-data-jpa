package tech.simter.start.springdatajpa.repository;

import tech.simter.start.springdatajpa.po.Entity1;

import java.util.List;

/**
 * @author RJ
 */
public interface Entity1CustomRepository {
  List<Entity1> findAllByName(String name);
}