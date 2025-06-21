package com.dangdang.check.infrastructure.employee;

import com.dangdang.check.domain.employee.EmployeeCommand;
import com.dangdang.check.domain.employee.EmployeeValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmployeeValidatorImpl implements EmployeeValidator {

    private final EmployeeJpaRepository employeeJpaRepository;

    @Override
    public void checkRegisterEmployee(EmployeeCommand.RegisterEmployeeRequest request) {
        checkDuplicateNickname(request.getNickname());
        checkDuplicateLoginId(request.getLoginId());
        checkDuplicateEmail(request.getEmail());
        checkDuplicateMobilePhone(request.getMobilePhone());
    }

    private void checkDuplicateNickname(String nickname) {
        employeeJpaRepository.findByNicknameAndIsDeletedFalse(nickname)
                .ifPresent(employee -> {
                    throw new IllegalArgumentException("이미 사용 중인 닉네임입니다: " + nickname);
                });
    }

    private void checkDuplicateMobilePhone(String mobilePhone) {
        employeeJpaRepository.findByMobilePhoneAndIsDeletedFalse(mobilePhone)
                .ifPresent(employee -> {
                    throw new IllegalArgumentException("이미 사용 중인 휴대폰 번호입니다: " + mobilePhone);
                });
    }

    private void checkDuplicateEmail(String email) {
        employeeJpaRepository.findByEmailAndIsDeletedFalse(email)
                .ifPresent(employee -> {
                    throw new IllegalArgumentException("이미 사용 중인 이메일입니다: " + email);
                });
    }

    private void checkDuplicateLoginId(String loginId) {
        employeeJpaRepository.findByLoginIdAndIsDeletedFalse(loginId)
                .ifPresent(employee -> {
                    throw new IllegalArgumentException("이미 사용 중인 로그인 ID입니다: " + loginId);
                });
    }

}
