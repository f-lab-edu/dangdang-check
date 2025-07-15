package com.dangdang.check.domain.employee;


import com.dangdang.check.domain.BaseEntity;
import com.dangdang.check.domain.store.StoreEntity;
import io.micrometer.common.util.StringUtils;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.security.InvalidParameterException;
import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "employees")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EmployeeEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(unique = true)
    private String nickname;
    @Column(unique = true)
    private String email;
    @Column(unique = true)
    private String loginId;
    private String password;
    @Column(unique = true)
    private String mobilePhone;
    @Enumerated(EnumType.STRING)
    private Role role = Role.DEFAULT;
    private boolean isDeleted = false;
    private LocalDateTime deletedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private StoreEntity store;

    @Builder
    public EmployeeEntity(String name, String nickname, String email, String loginId, String password, String mobilePhone) {
        if (isNotValidParams(name, nickname, email, loginId, password, mobilePhone)) {
            throw new InvalidParameterException();
        }

        this.name = name;
        this.nickname = nickname;
        this.email = email;
        this.loginId = loginId;
        this.password = password;
        this.mobilePhone = mobilePhone;
    }

    public void addStore(StoreEntity store) {
        this.store = store;
    }

    public void modifyProfile(String name, String nickname) {
        modifyName(name);
        modifyNickName(nickname);
    }

    public void modifyName(String name) {
        if (StringUtils.isNotBlank(name)) {
            this.name = name;
        }
    }

    public void modifyNickName(String nickname) {
        if (StringUtils.isNotBlank(nickname)) {
            this.nickname = nickname;
        }
    }

    public void modifyPassword(String password) {
        if (StringUtils.isNotBlank(password)) {
            this.password = password;
        }
    }

    private boolean isNotValidParams(String name, String nickname, String email, String loginId, String password, String mobilePhone) {
        return StringUtils.isBlank(name)
                || StringUtils.isBlank(nickname)
                || StringUtils.isBlank(email)
                || StringUtils.isBlank(loginId)
                || StringUtils.isBlank(password)
                || StringUtils.isBlank(mobilePhone);
    }
}
