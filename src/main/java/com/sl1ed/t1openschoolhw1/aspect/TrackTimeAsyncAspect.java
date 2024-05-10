package com.sl1ed.t1openschoolhw1.aspect;

import com.sl1ed.t1openschoolhw1.dto.MethodExecutionTimeDto;
import com.sl1ed.t1openschoolhw1.dto.enums.MethodAnnotationType;
import com.sl1ed.t1openschoolhw1.service.MethodExecutionTimeService;
import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class TrackTimeAsyncAspect {

  private final MethodExecutionTimeService methodExecutionTimeService;

  @Pointcut("@annotation(com.sl1ed.t1openschoolhw1.annotation.TrackTime)")
  public void loggingAsyncMethod() {
  }

  @Around("loggingAsyncMethod()")
  public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
    long startNano = System.nanoTime();
    long startMilli = System.currentTimeMillis();

    CompletableFuture<Object> result = CompletableFuture.supplyAsync(() -> {
      try {
        return joinPoint.proceed();
      } catch (Throwable throwable) {
        throw new RuntimeException(throwable);
      }
    });
    result.thenAcceptAsync((res) -> {
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
    });
    return result.get();
  }

}
