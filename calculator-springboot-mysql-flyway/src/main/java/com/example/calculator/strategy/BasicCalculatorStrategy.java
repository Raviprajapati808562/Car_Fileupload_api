package com.example.calculator.strategy;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class BasicCalculatorStrategy implements CalculatorStrategy {

    @Override
    public boolean supports(String expression) {

        return expression.contains("+")
                || expression.contains("-")
                || expression.contains("*")
                || expression.contains("/");
    }

    @Override
    public BigDecimal calculate(String expression) {

        expression = expression.trim();

        if (expression.contains("+")) {
            String[] parts = expression.split("\\+");

            validateTwoOperands(parts);

            BigDecimal first = new BigDecimal(parts[0].trim());
            BigDecimal second = new BigDecimal(parts[1].trim());

            return first.add(second);
        }

        if (expression.contains("-")) {
            String[] parts = expression.split("-");

            validateTwoOperands(parts);

            BigDecimal first = new BigDecimal(parts[0].trim());
            BigDecimal second = new BigDecimal(parts[1].trim());

            return first.subtract(second);
        }

        if (expression.contains("*")) {
            String[] parts = expression.split("\\*");

            validateTwoOperands(parts);

            BigDecimal first = new BigDecimal(parts[0].trim());
            BigDecimal second = new BigDecimal(parts[1].trim());

            return first.multiply(second);
        }

        if (expression.contains("/")) {
            String[] parts = expression.split("/");

            validateTwoOperands(parts);

            BigDecimal first = new BigDecimal(parts[0].trim());
            BigDecimal second = new BigDecimal(parts[1].trim());

            if (second.compareTo(BigDecimal.ZERO) == 0) {
                throw new IllegalArgumentException(
                        "Division by zero is not allowed");
            }

            return first.divide(
                    second,
                    10,
                    RoundingMode.HALF_UP
            );
        }

        throw new IllegalArgumentException(
                "Unsupported basic expression: " + expression);
    }

    private void validateTwoOperands(String[] parts) {

        if (parts.length != 2) {
            throw new IllegalArgumentException(
                    "Expression must contain exactly two operands");
        }
    }
}