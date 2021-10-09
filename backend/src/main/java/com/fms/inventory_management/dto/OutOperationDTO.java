package com.fms.inventory_management.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import com.fms.inventory_management.entities.OutOperation;
import com.fms.inventory_management.entities.User;

import lombok.Data;

@Data
public class OutOperationDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String id;
	private Instant instantMoment;
	private String costApplication;
	private BigDecimal total;
	private User user;

	private Set<TransactionDTO> products = new HashSet<>();

	public OutOperationDTO(OutOperation entity) {
		id = entity.getId();
		instantMoment = entity.getInstantMoment();
		costApplication = entity.getCostApplication();
		user = entity.getUser();
		products = entity.getProducts();
	}

}
