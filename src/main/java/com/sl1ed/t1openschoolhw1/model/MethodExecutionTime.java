package com.sl1ed.t1openschoolhw1.model;

import com.sl1ed.t1openschoolhw1.dto.enums.MethodAnnotationType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MethodExecutionTime {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String className;

  private String methodName;

  private LocalDateTime executionDate;

  private Long executionInNanoTime;

  private Long executionInMSTime;

  private MethodAnnotationType methodAnnotationType;

}
