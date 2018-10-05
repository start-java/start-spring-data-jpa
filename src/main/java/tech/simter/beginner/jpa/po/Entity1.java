package tech.simter.beginner.jpa.po;

import lombok.Data;

import javax.persistence.*;

/**
 * @author RJ
 */
@Entity
@Data
public class Entity1 {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  @Column(length = 100, nullable = false, unique = true)
  private String name;
  private String code;
  private String describe;
}