package com.example.fraud.service;

import com.example.fraud.entity.Fraud;
import com.example.fraud.repository.FraudRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class FraudService {

    private final FraudRepository fraudRepository;

    @Transactional
    public boolean isFraudulentCustomer(Long customerId) {
        Fraud fraud = Fraud.builder()
                .customerId(customerId)
                .isFraudster(false)
                .createdAt(LocalDateTime.now())
                .build();

        fraudRepository.save(fraud);

        return false;
    }
}
