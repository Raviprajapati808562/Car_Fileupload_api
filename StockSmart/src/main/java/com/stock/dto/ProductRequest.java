package com.stock.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.stocke.num.ProductType;

public class ProductRequest {

	private String sku;
	private String name;
	private String category;
	private BigDecimal unitPrice;
	private Long reorderLevel;
	private ProductType productType;
	private LocalDate expiryDate;
	private Integer warrantyMonths;

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Long getReorderLevel() {
		return reorderLevel;
	}

	public void setReorderLevel(Long reorderLevel) {
		this.reorderLevel = reorderLevel;
	}

	public ProductType getProductType() {
		return productType;
	}

	public void setProductType(ProductType productType) {
		this.productType = productType;
	}

	public LocalDate getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(LocalDate expiryDate) {
		this.expiryDate = expiryDate;
	}

	public Integer getWarrantyMonths() {
		return warrantyMonths;
	}

	public void setWarrantyMonths(Integer warrantyMonths) {
		this.warrantyMonths = warrantyMonths;
	}

}