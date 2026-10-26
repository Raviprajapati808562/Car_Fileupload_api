package com.example.calculator.entity;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "calculation_history")
public class CalculationHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 500)
	private String expression;

	@Column(nullable = false, precision = 30, scale = 10)
	private BigDecimal result;

	@Column(nullable = false)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSSSSS")
	private String calculatedAt;

	public CalculationHistory() {
	}

	public CalculationHistory(String expression, BigDecimal result, String calculatedAt) {
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
