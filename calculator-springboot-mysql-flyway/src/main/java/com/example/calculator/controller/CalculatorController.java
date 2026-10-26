package com.example.calculator.controller;

import com.example.calculator.dto.CalculationRequest;
import com.example.calculator.dto.CalculationResponse;
import com.example.calculator.dto.HistoryResponse;
import com.example.calculator.service.CalculatorService;
import com.example.calculator.service.HistoryService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/calculator")
public class CalculatorController {

    private final CalculatorService calculatorService;
    private final HistoryService historyService;

    public CalculatorController(CalculatorService calculatorService,
                                HistoryService historyService) {
        this.calculatorService = calculatorService;
        this.historyService = historyService;
    }

    @PostMapping("/calculate")
    public ResponseEntity<CalculationResponse> calculate(
            @Valid @RequestBody CalculationRequest request) {

        return ResponseEntity.ok(
                calculatorService.calculate(request.getExpression()));
    }

    @GetMapping("/history")
    public ResponseEntity<List<HistoryResponse>> history() {
        return ResponseEntity.ok(historyService.getHistory());
    }
}
