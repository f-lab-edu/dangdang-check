package com.dangdang.check.core.pet;

import com.dangdang.check.domain.pet.PetEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class PetFindService {

    private final PetJpaRepository petJpaRepository;

    @Transactional(readOnly = true)
    public List<PetEntity> findAllById(Set<Long> petIds) {
        return petJpaRepository.findAllByIdInAndIsDeletedFalse(petIds);
    }
}
