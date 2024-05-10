package com.sl1ed.t1openschoolhw1.repository;

import com.sl1ed.t1openschoolhw1.model.MethodExecutionTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MethodExecutionTimeRepository extends JpaRepository<MethodExecutionTime, Long> {

  List<MethodExecutionTime> findMethodExecutionTimesByClassNameAndAndMethodName(String className,
      String methodName);

}
