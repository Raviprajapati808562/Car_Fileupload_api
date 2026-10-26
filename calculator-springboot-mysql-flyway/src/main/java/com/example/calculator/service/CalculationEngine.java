package com.example.calculator.service;

import org.springframework.stereotype.Service;

import com.example.calculator.strategy.CalculatorStrategy;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CalculationEngine {

    private final List<CalculatorStrategy> strategies;

    public CalculationEngine(List<CalculatorStrategy> strategies) {
        this.strategies = strategies;
    }

    public BigDecimal calculate(String expression) {

        if (expression == null || expression.isBlank()) {
            throw new IllegalArgumentException(
                    "Expression cannot be empty");
        }

        return strategies.stream()
                .filter(strategy -> strategy.supports(expression))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Unsupported expression: " + expression))
                .calculate(expression);
    }
}