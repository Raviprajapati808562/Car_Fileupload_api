package com.stock.controller;

import com.stock.dto.WarehouseRequest;
import com.stock.entity.Warehouse;
import com.stock.repository.WarehouseRepository;
import com.stock.service.Warehouseservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/warehouses")
public class WarehouseController {
	@Autowired
	private Warehouseservice warehouseservice;

	@PostMapping("/saveWarehouseData")
	public ResponseEntity<ResponseEntity<Warehouse>> createWarehouse(@RequestBody WarehouseRequest request) {

		ResponseEntity<Warehouse> savedWarehouse = warehouseservice.saveWarehouseDetails(request);

		return ResponseEntity.ok(savedWarehouse);
	}
}