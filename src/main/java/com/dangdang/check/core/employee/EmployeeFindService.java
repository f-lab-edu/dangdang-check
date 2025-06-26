package com.dangdang.check.core.employee;

import com.dangdang.check.domain.employee.EmployeeEntity;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeFindService {

    private final EmployeeJpaRepository employeeJpaRepository;

    public EmployeeEntity findByLoginId(String loginId) {
        return employeeJpaRepository.findByLoginIdAndIsDeletedFalse(loginId)
                .orElseThrow((EntityNotFoundException::new));
    }

    public EmployeeEntity findByLoginIdAndPassword(String loginId, String password) {
        return employeeJpaRepository.findByLoginIdAndPasswordAndIsDeletedFalse(loginId, password)
                .orElseThrow(() -> new EntityNotFoundException("로그인 ID 또는 비밀번호가 잘못되었습니다."));
    }
}