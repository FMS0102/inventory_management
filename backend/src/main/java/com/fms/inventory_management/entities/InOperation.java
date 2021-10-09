package com.fms.inventory_management.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fms.inventory_management.dto.TransactionDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Data
@Document(collection = "inputs")
public class InOperation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	private Instant instantMoment;
	private String costCenter;
	private User user;

	Set<TransactionDTO> products = new HashSet<>();

	public InOperation(String id, Instant instantMoment, String costCenter, User user) {
		this.id = id;
		this.instantMoment = instantMoment;
		this.costCenter = costCenter;
		this.user = user;
	}

	public void addProducts(TransactionDTO prod) {
		products.add(prod);
	}

	public void removeProducts(TransactionDTO prod) {
		products.remove(prod);
	}

}
