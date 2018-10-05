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
  @Column(nullable = false, unique = true, length = 100)
  private String code;
  private String name;
  private String describe;
}