package com.example.calculator.parser;

import com.example.calculator.operation.OperationFactory;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ExpressionParser {

    private final OperationFactory operationFactory;
    private String expression;
    private int position;

    public ExpressionParser(OperationFactory operationFactory) {
        this.operationFactory = operationFactory;
    }

    public BigDecimal parse(String input) {

        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException("Expression must not be blank");
        }

        this.expression = input.replaceAll("\\s+", "");
        this.position = 0;

        BigDecimal result = parseExpression();

        if (position != expression.length()) {
            throw new IllegalArgumentException(
                    "Invalid expression near position " + position);
        }

        return result.stripTrailingZeros();
    }

    // Handles + and -
    private BigDecimal parseExpression() {

        BigDecimal result = parseTerm();

        while (position < expression.length()) {

            char operator = expression.charAt(position);

            if (operator != '+' && operator != '-') {
                break;
            }

            position++;

            BigDecimal right = parseTerm();

            result = operationFactory
                    .getOperation(operator)
                    .calculate(result, right);
        }

        return result;
    }

    // Handles * and /
    private BigDecimal parseTerm() {

        BigDecimal result = parseFactor();

        while (position < expression.length()) {

            char operator = expression.charAt(position);

            if (operator != '*' && operator != '/') {
                break;
            }

            position++;

            BigDecimal right = parseFactor();

            result = operationFactory
                    .getOperation(operator)
                    .calculate(result, right);
        }

        return result;
    }

    // Handles numbers and parentheses
    private BigDecimal parseFactor() {

        if (position >= expression.length()) {
            throw new IllegalArgumentException("Expected number");
        }

        char current = expression.charAt(position);

        if (current == '(') {

            position++;

            BigDecimal result = parseExpression();

            if (position >= expression.length()
                    || expression.charAt(position) != ')') {

                throw new IllegalArgumentException(
                        "Missing closing parenthesis");
            }

            position++;

            return result;
        }

        return parseNumber();
    }

    private BigDecimal parseNumber() {

        int start = position;

        // Basic support for unary +/-
        if (position < expression.length()
                && (expression.charAt(position) == '+'
                || expression.charAt(position) == '-')) {

            position++;
        }

        boolean hasDigit = false;
        boolean hasDecimal = false;

        while (position < expression.length()) {

            char current = expression.charAt(position);

            if (Character.isDigit(current)) {
                hasDigit = true;
                position++;
            } else if (current == '.' && !hasDecimal) {
                hasDecimal = true;
                position++;
            } else {
                break;
            }
        }

        if (!hasDigit) {
            throw new IllegalArgumentException(
                    "Expected number at position " + start);
        }

        return new BigDecimal(
                expression.substring(start, position));
    }
}
