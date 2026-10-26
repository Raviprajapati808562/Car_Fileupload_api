package com.stock.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stock.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	Optional<Product> findBySku(String sku);
}