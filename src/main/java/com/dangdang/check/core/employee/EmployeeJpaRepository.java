package com.dangdang.check.core.employee;

import com.dangdang.check.domain.employee.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;



public interface EmployeeJpaRepository extends JpaRepository<EmployeeEntity, Long> {

}