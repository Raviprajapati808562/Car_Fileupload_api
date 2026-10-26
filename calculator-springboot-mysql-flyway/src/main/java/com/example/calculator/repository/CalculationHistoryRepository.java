package com.example.calculator.repository;

import com.example.calculator.entity.CalculationHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalculationHistoryRepository
        extends JpaRepository<CalculationHistory, Long> {
}
