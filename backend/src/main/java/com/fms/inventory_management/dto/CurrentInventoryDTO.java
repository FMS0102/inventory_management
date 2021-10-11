package com.fms.inventory_management.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fms.inventory_management.entities.CurrentInventory;
import com.fms.inventory_management.entities.enums.MaterialType;
import com.fms.inventory_management.entities.enums.UnitMeasurement;

import lombok.Data;

@Data
public class CurrentInventoryDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long prodCod;
	private String name;
	private UnitMeasurement unitMeasurement;
	private BigDecimal quantity;
	private BigDecimal price;
	private MaterialType materialType;

	public CurrentInventoryDTO(CurrentInventory obj) {
		prodCod = obj.getProdCod();
		name = obj.getName();
		unitMeasurement = obj.getUnitMeasurement();
		quantity = obj.getQuantity();
		price = obj.getPrice();
		materialType = obj.getMaterialType();
	}

}
