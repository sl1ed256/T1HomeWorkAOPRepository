package com.sl1ed.t1openschoolhw1.service.impl;

import com.sl1ed.t1openschoolhw1.dto.MethodExecutionTimeDto;
import com.sl1ed.t1openschoolhw1.mapper.MethodExecutionTimeMapper;
import com.sl1ed.t1openschoolhw1.repository.MethodExecutionTimeRepository;
import com.sl1ed.t1openschoolhw1.service.MethodExecutionTimeService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class MethodExecutionTimeServiceImpl implements MethodExecutionTimeService {

  private final MethodExecutionTimeRepository methodExecutionTimeRepository;

  private final MethodExecutionTimeMapper methodExecutionTimeMapper;

  @Override
  public Long save(MethodExecutionTimeDto methodExecutionTimeDto) {
    return methodExecutionTimeRepository.save(
        methodExecutionTimeMapper.convertToEntity(methodExecutionTimeDto)).getId();
  }

  @Transactional(readOnly = true)
  @Override
  public List<MethodExecutionTimeDto> getAllByClassAndMethodName(String className,
      String methodName) {
    return methodExecutionTimeMapper.convertToDtoList(
        methodExecutionTimeRepository.findMethodExecutionTimesByClassNameAndAndMethodName(className,
            methodName));
  }

  @Transactional(readOnly = true)
  @Override
  public List<MethodExecutionTimeDto> getAll() {
    return methodExecutionTimeMapper.convertToDtoList(methodExecutionTimeRepository.findAll());
  }
}
