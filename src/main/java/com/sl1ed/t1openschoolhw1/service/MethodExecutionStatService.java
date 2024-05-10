package com.sl1ed.t1openschoolhw1.service;

import com.sl1ed.t1openschoolhw1.dto.MethodExecutionStatDto;

public interface MethodExecutionStatService {

  MethodExecutionStatDto getByClassAndMethodName(String className, String methodName);

}
