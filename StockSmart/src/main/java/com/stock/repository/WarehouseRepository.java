package com.stock.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stock.entity.Warehouse;

public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {

	Optional<Warehouse> findByCode(String warehouseCode);
}
