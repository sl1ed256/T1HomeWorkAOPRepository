package com.sl1ed.t1openschoolhw1.service;

import com.sl1ed.t1openschoolhw1.dto.MethodExecutionTimeDto;
import java.util.List;

public interface MethodExecutionTimeService {

  Long save(MethodExecutionTimeDto methodExecutionTimeDto);

  List<MethodExecutionTimeDto> getAllByClassAndMethodName(String className, String methodName);

  List<MethodExecutionTimeDto> getAll();

}
