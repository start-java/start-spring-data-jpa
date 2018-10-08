package tech.simter.start.springdatajpa.po;

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
  private Status statusOrdinal;
  @Enumerated(EnumType.STRING)
  private Status statusName;
  @Convert(converter = StatusConverter.class)
  private Status status;
  @Column(nullable = false, unique = true, length = 100)
  private String code;
  private String name;
  private String describe;

  public enum Status {
    Todo(16),
    Done(32);

    private Integer value;

    Status(Integer value) {
      this.value = value;
    }

    public Integer value() {
      return this.value;
    }
  }

  // @Converter(autoApply = true)
  // If set `@Converter(autoApply = true)`, no need to use `@Convert(converter = StatusConverter.class)` on property
  public static class StatusConverter implements AttributeConverter<Status, Integer> {
    @Override
    public Integer convertToDatabaseColumn(Status attribute) {
      if (attribute == null) return null;
      else return attribute.value();
    }

    @Override
    public Status convertToEntityAttribute(Integer dbData) {
      if (dbData == null) return null;
      else {
        for (Status status : Status.values()) if (status.value().equals(dbData)) return status;
        throw new IllegalArgumentException("Can't convert db value '" + dbData + "' to " + Status.class.getSimpleName() + ".");
      }
    }
  }
}