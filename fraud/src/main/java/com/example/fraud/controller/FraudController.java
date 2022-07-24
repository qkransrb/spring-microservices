package com.example.fraud.controller;

import com.example.clients.fraud.dto.FraudCheckResponse;
import com.example.fraud.service.FraudService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/fraud-check")
@RequiredArgsConstructor
public class FraudController {

    private final FraudService fraudService;

    @GetMapping("/{customerId}")
    public FraudCheckResponse isFraudster(@PathVariable("customerId") Long customerId) {
        log.info("fraud check customer id {}", customerId);
        boolean isFraudster = fraudService.isFraudulentCustomer(customerId);
        return new FraudCheckResponse(isFraudster);
    }
}
