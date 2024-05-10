package com.sl1ed.t1openschoolhw1.controller;

import com.sl1ed.t1openschoolhw1.dto.MethodExecutionStatDto;
import com.sl1ed.t1openschoolhw1.service.MethodExecutionStatService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stat")
@Tag(name = "Stat API", description = "API for stats")
public class MethodExecutionStatController {

  private final MethodExecutionStatService methodExecutionStatService;

  @GetMapping("/{className}/{methodName}")
  @Operation(summary = "Get stats for method", description = "Get stats for method by class and method name.", operationId = "getStatF")
  @ApiResponses({@ApiResponse(responseCode = "200", description = "Successful operation"),})
  public ResponseEntity<MethodExecutionStatDto> getStatByClassAndMethodName(
      @Parameter(description = "Classname of the method", required = true) @PathVariable("className") String className,
      @Parameter(description = "Method name", required = true) @PathVariable("methodName") String methodName) {
    return ResponseEntity.ok()
        .body(methodExecutionStatService.getByClassAndMethodName(className, methodName));
  }

}
