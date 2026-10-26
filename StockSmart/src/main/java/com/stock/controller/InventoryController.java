package com.stock.controller;

import com.stock.dto.StockInRequest;
import com.stock.service.InventoryService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @PostMapping("/stock-in")
    public ResponseEntity<String> stockIn(
            @RequestBody StockInRequest request) {

        inventoryService.stockIn(request);

        return ResponseEntity.ok(
                "Stock received successfully"
        );
    }
}