package com.example.calculator.operation;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class OperationFactory {

    private final Map<String, Operation> operations;

    public OperationFactory(AdditionOperation addition,
                            SubtractionOperation subtraction,
                            MultiplicationOperation multiplication,
                            DivisionOperation division) {

        this.operations = Map.of(
                "+", addition,
                "-", subtraction,
                "*", multiplication,
                "/", division
        );
    }

    public Operation getOperation(char operator) {
        Operation operation = operations.get(String.valueOf(operator));

        if (operation == null) {
            throw new IllegalArgumentException(
                    "Unsupported operator: " + operator);
        }

        return operation;
    }
}
