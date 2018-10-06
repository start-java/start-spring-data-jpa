package tech.simter.start.springdatajpa.repository;

import java.util.Optional;

/**
 * @author RJ
 */
public interface Entity1ContrastMethods {
  /**
   * Get the max code starting with specific code prefix.
   */
  Optional<String> getMaxCode(String codePrefix);
}