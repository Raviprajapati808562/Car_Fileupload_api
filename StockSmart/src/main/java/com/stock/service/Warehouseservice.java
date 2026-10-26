package com.stock.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.stock.dto.WarehouseRequest;
import com.stock.entity.Warehouse;
import com.stock.repository.WarehouseRepository;

@Service
public class Warehouseservice {

	@Autowired
	private WarehouseRepository repository;

	public ResponseEntity<Warehouse> saveWarehouseDetails(WarehouseRequest request) {
		Warehouse warehouse = new Warehouse();

		warehouse.setCode(request.getCode());
		warehouse.setName(request.getName());
		warehouse.setCity(request.getCity());

		Warehouse savedWarehouse = repository.save(warehouse);

		return ResponseEntity.ok(savedWarehouse);
	}
}