package com.example.calculator.dto;

import java.math.BigDecimal;

public class CalculationResponse {

    private String expression;
    private BigDecimal result;
    private String calculatedAt;

    public CalculationResponse() {
    }

    public CalculationResponse(String expression, BigDecimal result, String calculatedAt) {
        this.expression = expression;
        this.result = result;
        this.calculatedAt = calculatedAt;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public BigDecimal getResult() {
        return result;
    }

    public void setResult(BigDecimal result) {
        this.result = result;
    }

    public String getCalculatedAt() {
        return calculatedAt;
    }

    public void setCalculatedAt(String calculatedAt) {
        this.calculatedAt = calculatedAt;
    }
}
