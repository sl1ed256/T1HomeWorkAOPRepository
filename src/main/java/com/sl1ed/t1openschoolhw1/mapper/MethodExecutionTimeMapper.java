package com.sl1ed.t1openschoolhw1.mapper;

import com.sl1ed.t1openschoolhw1.dto.MethodExecutionTimeDto;
import com.sl1ed.t1openschoolhw1.model.MethodExecutionTime;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MethodExecutionTimeMapper {

  MethodExecutionTimeDto convertToDto(MethodExecutionTime methodExecutionTime);

  MethodExecutionTime convertToEntity(MethodExecutionTimeDto methodExecutionTimeDto);

  List<MethodExecutionTimeDto> convertToDtoList(List<MethodExecutionTime> methodExecutionTimes);

}
