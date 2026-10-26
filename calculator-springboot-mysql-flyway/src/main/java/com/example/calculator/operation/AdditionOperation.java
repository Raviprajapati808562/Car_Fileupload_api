package com.example.calculator.operation;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class AdditionOperation implements Operation {

    @Override
    public BigDecimal calculate(BigDecimal left, BigDecimal right) {
        return left.add(right);
    }
}
