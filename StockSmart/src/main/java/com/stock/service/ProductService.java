package com.stock.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.stock.entity.Product;
import com.stock.repository.ProductRepository;
@Service
public class ProductService {

	private final ProductRepository productRepository;

	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	public ResponseEntity<Product> postProductsData(Product request) {
		Product product = new Product();

		product.setSku(request.getSku());
		product.setName(request.getName());
		product.setCategory(request.getCategory());
		product.setUnitPrice(request.getUnitPrice());
		product.setReorderLevel(request.getReorderLevel());
		product.setProductType(request.getProductType());
		product.setExpiryDate(request.getExpiryDate());
		product.setWarrantyMonths(request.getWarrantyMonths());

		return ResponseEntity.ok(productRepository.save(product));
	}
}