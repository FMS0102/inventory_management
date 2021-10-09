package com.fms.inventory_management.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fms.inventory_management.entities.enums.MaterialType;
import com.fms.inventory_management.entities.enums.UnitMeasurement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Data
@Document(collection = "products")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	private Long prodCod;
	private String name;
	private UnitMeasurement unitMeasurement;
	private BigDecimal averagePrice;
	private MaterialType materialType;
}
