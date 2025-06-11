package com.dangdang.check.domain.employee;


import com.dangdang.check.domain.BaseEntity;
import com.dangdang.check.domain.store.Store;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "employees")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Employee extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String nickname;
    private String email;
    private String loginId;
    private String password;
    private String mobilePhone;
    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;
}
