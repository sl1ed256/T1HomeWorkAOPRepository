package com.sl1ed.t1openschoolhw1.dto;

import com.sl1ed.t1openschoolhw1.dto.enums.MethodAnnotationType;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class MethodExecutionTimeDto {

  private String className;

  private String methodName;

  private LocalDateTime executionDate;

  private Long executionInNanoTime;

  private Long executionInMSTime;

  private MethodAnnotationType methodAnnotationType;

}
