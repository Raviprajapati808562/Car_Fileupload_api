package com.example.calculator.parser;

import com.example.calculator.operation.AdditionOperation;
import com.example.calculator.operation.DivisionOperation;
import com.example.calculator.operation.MultiplicationOperation;
import com.example.calculator.operation.OperationFactory;
import com.example.calculator.operation.SubtractionOperation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ExpressionParserTest {

    private ExpressionParser parser;

    @BeforeEach
    void setUp() {
        OperationFactory factory = new OperationFactory(
                new AdditionOperation(),
                new SubtractionOperation(),
                new MultiplicationOperation(),
                new DivisionOperation()
        );

        parser = new ExpressionParser(factory);
    }

    @Test
    void shouldRespectBodmas() {
        assertEquals(
                new BigDecimal("3"),
                parser.parse("1+2*3-4")
        );
    }

    @Test
    void shouldHandleParentheses() {
        assertEquals(
                new BigDecimal("21"),
                parser.parse("(1+2)*7")
        );
    }

    @Test
    void shouldCalculateAssignmentExpression() {
        assertEquals(
                new BigDecimal("47"),
                parser.parse("1-2+3-5*4+5*(6+7)")
        );
    }

    @Test
    void shouldHandleDivision() {
        assertEquals(
                new BigDecimal("5"),
                parser.parse("20/4")
        );
    }

    @Test
    void shouldRejectDivisionByZero() {
        assertThrows(
                ArithmeticException.class,
                () -> parser.parse("10/0")
        );
    }

    @Test
    void shouldRejectInvalidExpression() {
        assertThrows(
                IllegalArgumentException.class,
                () -> parser.parse("10+")
        );
    }
}
