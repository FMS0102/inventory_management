package com.fms.inventory_management.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fms.inventory_management.entities.enums.MaterialType;
import com.fms.inventory_management.entities.enums.UnitMeasurement;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Document(collection = "inventory")
public class CurrentInventory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long prodCod;
	private String name;
	private UnitMeasurement unitMeasurement;
	private BigDecimal quantity;
	private BigDecimal price;
	private MaterialType materialType;

	public CurrentInventory(BigDecimal quantity, BigDecimal price, Product product) {
		this.prodCod = product.getProdCod();
		this.name = product.getName();
		this.unitMeasurement = product.getUnitMeasurement();
		this.quantity = quantity;
		this.price = price;
		this.materialType = product.getMaterialType();

	}

}
