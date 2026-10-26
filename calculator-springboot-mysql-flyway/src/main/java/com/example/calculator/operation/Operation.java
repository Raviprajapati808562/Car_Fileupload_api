package com.example.calculator.operation;

import java.math.BigDecimal;

public interface Operation {

    BigDecimal calculate(BigDecimal left, BigDecimal right);
}
