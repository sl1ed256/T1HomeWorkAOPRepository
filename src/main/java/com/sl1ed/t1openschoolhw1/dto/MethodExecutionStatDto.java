package com.sl1ed.t1openschoolhw1.dto;

import lombok.Data;

@Data
public class MethodExecutionStatDto extends MethodExecutionTimeDto {

  private Long minExecuteTimeMilli;

  private Long maxExecuteTimeMilli;

  private Long avgExecuteTimeMilli;

  private Long minExecuteTimeNano;

  private Long maxExecuteTimeNano;

  private Long avgExecuteTimeNano;

}
