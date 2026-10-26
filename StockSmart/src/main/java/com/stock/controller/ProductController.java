package com.stock.controller;

import org.jspecify.annotations.Nullable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stock.dto.ProductRequest;
import com.stock.entity.Product;
import com.stock.repository.ProductRepository;
import com.stock.service.ProductService;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

	private final ProductService productService;

	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@PostMapping("/saveProductData")
	public ResponseEntity<Product> createProduct(@RequestBody ProductRequest request) {

		Product product = new Product();

		product.setSku(request.getSku());
		product.setName(request.getName());
		product.setCategory(request.getCategory());
		product.setUnitPrice(request.getUnitPrice());
		product.setReorderLevel(request.getReorderLevel());
		product.setProductType(request.getProductType());
		product.setExpiryDate(request.getExpiryDate());
		product.setWarrantyMonths(request.getWarrantyMonths());

		ResponseEntity<Product> ok = productService.postProductsData(product);
		return ok;
	}
}