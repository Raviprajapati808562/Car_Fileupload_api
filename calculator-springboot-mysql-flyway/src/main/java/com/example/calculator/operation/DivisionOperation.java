package com.example.calculator.operation;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class DivisionOperation implements Operation {

    @Override
    public BigDecimal calculate(BigDecimal left, BigDecimal right) {

        if (right.compareTo(BigDecimal.ZERO) == 0) {
            throw new ArithmeticException("Cannot divide by zero");
        }

        return left.divide(right, 10, RoundingMode.HALF_UP)
                .stripTrailingZeros();
    }
}
