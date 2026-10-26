package com.example.calculator.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.calculator.dto.CalculationResponse;
import com.example.calculator.entity.CalculationHistory;
import com.example.calculator.repository.CalculationHistoryRepository;

@Service
public class CalculatorService {

	private final CalculationEngine calculationEngine;
	private final CalculationHistoryRepository historyRepository;

	public CalculatorService(CalculationEngine calculationEngine, CalculationHistoryRepository historyRepository) {
		this.calculationEngine = calculationEngine;
		this.historyRepository = historyRepository;
	}


	@Transactional
	public CalculationResponse calculate(String expression) {

		BigDecimal result = calculationEngine.calculate(expression);

		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
//        LocalDateTime now = LocalDateTime.now();
		String dateq = sdf.format(date);
		CalculationHistory history = new CalculationHistory(expression, result, dateq);

		historyRepository.save(history);

		return new CalculationResponse(expression, result, dateq);
	}
}
