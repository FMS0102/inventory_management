package com.fms.inventory_management.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

import com.fms.inventory_management.entities.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Data
public class TransactionDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private BigDecimal quantity;
	private BigDecimal price;
	private BigDecimal total;
	private BigDecimal savingPurchase;
	private Product product;

	public TransactionDTO(BigDecimal quantity, BigDecimal price, Product product) {
		super();
		this.quantity = quantity;
		this.price = price;
		this.product = product;
		this.total = totalOperation();
		this.savingPurchase = savingPurchase();
	}

	public BigDecimal totalOperation() {
		return quantity.multiply(price);
	}

	public Product getProduct(Product operation) {
		return product;
	}

	public BigDecimal savingPurchase() {
		BigDecimal result = price.subtract(product.getAveragePrice());
		return result.divide(product.getAveragePrice(), 3, RoundingMode.HALF_UP);
	}
}
