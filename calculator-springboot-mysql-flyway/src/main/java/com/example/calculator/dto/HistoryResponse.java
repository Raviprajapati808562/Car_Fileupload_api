package com.example.calculator.dto;

import java.math.BigDecimal;

public class HistoryResponse {

    private Long id;
    private String expression;
    private BigDecimal result;
    private String calculatedAt;

    public HistoryResponse() {
    }

    public HistoryResponse(Long id, String expression, BigDecimal result, String calculatedAt) {
        this.id = id;
        this.expression = expression;
        this.result = result;
        this.calculatedAt = calculatedAt;
    }

    public Long getId() {
        return id;
    }

    public String getExpression() {
        return expression;
    }

    public BigDecimal getResult() {
        return result;
    }

    public String getCalculatedAt() {
        return calculatedAt;
    }
}
