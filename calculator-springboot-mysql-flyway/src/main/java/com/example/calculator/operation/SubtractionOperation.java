package com.example.calculator.operation;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class SubtractionOperation implements Operation {

    @Override
    public BigDecimal calculate(BigDecimal left, BigDecimal right) {
        return left.subtract(right);
    }
}
