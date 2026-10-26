package com.example.calculator.strategy;

import java.math.BigDecimal;

public interface CalculatorStrategy {

    boolean supports(String expression);

    BigDecimal calculate(String expression);
}