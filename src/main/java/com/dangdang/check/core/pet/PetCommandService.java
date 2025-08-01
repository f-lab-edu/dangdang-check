package com.dangdang.check.core.pet;

import com.dangdang.check.domain.pet.PetEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PetCommandService {

    private final PetJpaRepository petJpaRepository;

    @Transactional
    public PetEntity save(PetEntity pet) {
        return petJpaRepository.save(pet);
    }
}
