package com.example.calculator.service;

import com.example.calculator.dto.HistoryResponse;
import com.example.calculator.entity.CalculationHistory;
import com.example.calculator.repository.CalculationHistoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoryService {

    private final CalculationHistoryRepository repository;

    public HistoryService(CalculationHistoryRepository repository) {
        this.repository = repository;
    }

    public List<HistoryResponse> getHistory() {

        return repository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    private HistoryResponse toResponse(CalculationHistory history) {

        return new HistoryResponse(
                history.getId(),
                history.getExpression(),
                history.getResult(),
                history.getCalculatedAt()
        );
    }
}
