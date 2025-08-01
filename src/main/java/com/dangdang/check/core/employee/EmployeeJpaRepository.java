package com.dangdang.check.core.employee;

import com.dangdang.check.domain.employee.EmployeeEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface EmployeeJpaRepository extends JpaRepository<EmployeeEntity, Long> {

    @EntityGraph(attributePaths = {"store", "store.businessInfo"})
    Optional<EmployeeEntity> findByLoginIdAndIsDeletedFalse(String loginId);
    boolean existsByEmail(String email);
    boolean existsByMobilePhone(String mobilePhone);

}