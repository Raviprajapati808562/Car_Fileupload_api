package com.stock.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stock.entity.Inventory;
import com.stock.entity.Product;
import com.stock.entity.Warehouse;

public interface InventoryRepository extends JpaRepository<Inventory,Long>{

	Optional<Inventory> findByProductAndWarehouse(Product product, Warehouse warehouse);


}
