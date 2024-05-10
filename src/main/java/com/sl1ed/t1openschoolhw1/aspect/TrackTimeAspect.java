package com.sl1ed.t1openschoolhw1.aspect;

import com.sl1ed.t1openschoolhw1.dto.MethodExecutionTimeDto;
import com.sl1ed.t1openschoolhw1.dto.enums.MethodAnnotationType;
import com.sl1ed.t1openschoolhw1.service.MethodExecutionTimeService;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class TrackTimeAspect {

  private final MethodExecutionTimeService methodExecutionTimeService;

  @Pointcut("@annotation(com.sl1ed.t1openschoolhw1.annotation.TrackTime)")
  public void loggingMethod() {
  }

  @Around("loggingMethod()")
  public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
    long startNano = System.nanoTime();
    long startMilli = System.currentTimeMillis();

    Object proceed = joinPoint.proceed();

    long executionTimeNano = System.nanoTime() - startNano;
    long executionTimeMilli = System.currentTimeMillis() - startMilli;

    var methodExecutionTime = new MethodExecutionTimeDto();
    methodExecutionTime.setClassName(joinPoint.getTarget().getClass().getName());
    methodExecutionTime.setMethodName(joinPoint.getSignature().getName());
    methodExecutionTime.setExecutionDate(LocalDateTime.now());
    methodExecutionTime.setExecutionInNanoTime(executionTimeNano);
    methodExecutionTime.setExecutionInMSTime(executionTimeMilli);
    methodExecutionTime.setMethodAnnotationType(MethodAnnotationType.NOT_ASYNC);
    methodExecutionTimeService.save(methodExecutionTime);
    return proceed;
  }

}
