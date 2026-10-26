package com.example.calculator.strategy;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ScientificCalculatorStrategy implements CalculatorStrategy {

	@Override
	public boolean supports(String expression) {

		String exp = expression.trim().toLowerCase();

		return exp.startsWith("sqrt(") || exp.startsWith("pow(") || exp.startsWith("sin(") || exp.startsWith("cos(")
				|| exp.startsWith("square(") || exp.startsWith("log(");
	}

	@Override
	public BigDecimal calculate(String expression) {

		String exp = expression.trim().toLowerCase();

		if (exp.startsWith("sqrt(")) {
			double number = extractSingleValue(exp);

			if (number < 0) {
				throw new IllegalArgumentException("Square root of a negative number is not allowed");
			}

			return BigDecimal.valueOf(Math.sqrt(number));
		}
		if (exp.startsWith("square(")) {

			double number = extractSingleValue(exp);

			return BigDecimal.valueOf(number * number);
		}
		if (exp.startsWith("sin(")) {
			double number = extractSingleValue(exp);

			return BigDecimal.valueOf(Math.sin(Math.toRadians(number)));
		}

		if (exp.startsWith("cos(")) {
			double number = extractSingleValue(exp);

			return BigDecimal.valueOf(Math.cos(Math.toRadians(number)));
		}

		if (exp.startsWith("log(")) {
			double number = extractSingleValue(exp);

			if (number <= 0) {
				throw new IllegalArgumentException("Logarithm is defined only for positive numbers");
			}

			return BigDecimal.valueOf(Math.log10(number));
		}

		if (exp.startsWith("pow(")) {
			return calculatePower(exp);
		}

		throw new IllegalArgumentException("Unsupported scientific expression: " + expression);
	}

	private double extractSingleValue(String expression) {

		validateParentheses(expression);

		String value = expression.substring(expression.indexOf('(') + 1, expression.lastIndexOf(')'));

		if (value.contains(",")) {
			throw new IllegalArgumentException("Only one argument is expected");
		}

		return Double.parseDouble(value.trim());
	}

	private BigDecimal calculatePower(String expression) {

		validateParentheses(expression);

		String values = expression.substring(expression.indexOf('(') + 1, expression.lastIndexOf(')'));

		String[] parts = values.split(",");

		if (parts.length != 2) {
			throw new IllegalArgumentException("pow() requires two arguments");
		}

		double base = Double.parseDouble(parts[0].trim());
		double exponent = Double.parseDouble(parts[1].trim());

		return BigDecimal.valueOf(Math.pow(base, exponent));
	}

	private void validateParentheses(String expression) {

		if (!expression.endsWith(")")) {
			throw new IllegalArgumentException("Invalid scientific expression");
		}
	}
}