package com.sl1ed.t1openschoolhw1.service.impl;

import com.sl1ed.t1openschoolhw1.dto.MethodExecutionStatDto;
import com.sl1ed.t1openschoolhw1.dto.MethodExecutionTimeDto;
import com.sl1ed.t1openschoolhw1.dto.enums.MethodAnnotationType;
import com.sl1ed.t1openschoolhw1.service.MethodExecutionStatService;
import com.sl1ed.t1openschoolhw1.service.MethodExecutionTimeService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class MethodExecutionStatServiceImpl implements MethodExecutionStatService {

  private final MethodExecutionTimeService methodExecutionTimeService;

  @Transactional(readOnly = true)
  @Override
  public MethodExecutionStatDto getByClassAndMethodName(String className, String methodName) {
    List<MethodExecutionTimeDto> methodData = methodExecutionTimeService.getAllByClassAndMethodName(
        className, methodName);
    long sumMilli = 0;
    long maxMilli = Long.MIN_VALUE;
    long minMilli = Long.MAX_VALUE;
    long averageMilli = 0;

    long sumNano = 0;
    long maxNano = Long.MIN_VALUE;
    long minNano = Long.MAX_VALUE;
    long averageNano = 0;
    var methodStat = new MethodExecutionStatDto();
    if (!methodData.isEmpty()) {
      for (MethodExecutionTimeDto data : methodData) {
        long executeTimeMilli = data.getExecutionInMSTime();
        sumMilli += executeTimeMilli;
        maxMilli = Math.max(maxMilli, executeTimeMilli);
        minMilli = Math.min(minMilli, executeTimeMilli);

        long executeTimeNano = data.getExecutionInNanoTime();
        sumNano += executeTimeNano;
        maxNano = Math.max(maxNano, executeTimeNano);
        minNano = Math.min(minNano, executeTimeNano);
      }

      int size = methodData.size();
      averageMilli = sumMilli / size;

      averageNano = sumNano / size;

      methodStat.setClassName(className);
      methodStat.setMethodName(methodName);
      methodStat.setMethodAnnotationType(methodData.stream().findAny()
          .map(MethodExecutionTimeDto::getMethodAnnotationType).orElse(
              MethodAnnotationType.NOT_ASYNC));
      methodStat.setMaxExecuteTimeMilli(maxMilli);
      methodStat.setMinExecuteTimeMilli(minMilli);
      methodStat.setAvgExecuteTimeMilli(averageMilli);

      methodStat.setMaxExecuteTimeNano(maxNano);
      methodStat.setMinExecuteTimeNano(minNano);
      methodStat.setAvgExecuteTimeNano(averageNano);
    }
    return methodStat;
  }
}
