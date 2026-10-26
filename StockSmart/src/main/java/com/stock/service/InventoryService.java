package com.stock.service;

import com.stock.dto.StockInRequest;
import com.stock.entity.*;
import com.stock.globleexception.BusinessException;
import com.stock.globleexception.ResourceNotFoundException;
import com.stock.repository.InventoryRepository;
import com.stock.repository.ProductRepository;
import com.stock.repository.StockMovementRepository;
import com.stock.repository.WarehouseRepository;
import com.stocke.num.MovementType;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class InventoryService {

	private final ProductRepository productRepository;
	private final WarehouseRepository warehouseRepository;
	private final InventoryRepository inventoryRepository;
	private final StockMovementRepository movementRepository;

	public InventoryService(ProductRepository productRepository, WarehouseRepository warehouseRepository,
			InventoryRepository inventoryRepository, StockMovementRepository movementRepository) {

		this.productRepository = productRepository;
		this.warehouseRepository = warehouseRepository;
		this.inventoryRepository = inventoryRepository;
		this.movementRepository = movementRepository;
	}

	@Transactional
	public void stockIn(StockInRequest request) {

		if (request.getQuantity() == null || request.getQuantity() <= 0) {

			throw new BusinessException("Quantity must be greater than zero"+request.getQuantity());
		}

		Product product = productRepository.findBySku(request.getSku())
				.orElseThrow(() -> new ResourceNotFoundException("Product not found: " + request.getSku()));

		Warehouse warehouse = warehouseRepository.findByCode(request.getWarehouseCode())
				.orElseThrow(() -> new ResourceNotFoundException("Warehouse not found: " + request.getWarehouseCode()));

		Inventory inventory = inventoryRepository.findByProductAndWarehouse(product, warehouse).orElse(null);

		if (inventory == null) {

			inventory = new Inventory();

			inventory.setProduct(product);
			inventory.setWarehouse(warehouse);
			inventory.setQuantity(request.getQuantity());

		} else {

			inventory.setQuantity(inventory.getQuantity() + request.getQuantity());
		}

		inventoryRepository.save(inventory);

		StockMovement movement = new StockMovement();

		movement.setProduct(product);
		movement.setMovementType(MovementType.IN);
		movement.setQuantity(request.getQuantity());
		movement.setTimestamp(LocalDateTime.now());
		movement.setUsername(request.getUsername());
		movement.setWarehouse(warehouse);
		movement.setSupplier(request.getSupplier());

		movementRepository.save(movement);
	}
}