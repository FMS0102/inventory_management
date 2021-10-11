package com.fms.inventory_management.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import com.fms.inventory_management.entities.InOperation;
import com.fms.inventory_management.entities.User;

import lombok.Data;

@Data
public class InOperationDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String id;
	private Instant instantMoment;
	private String costCenter;
	private BigDecimal total;
	private User user;

	private Set<TransactionDTO> products = new HashSet<>();

	public InOperationDTO(InOperation entity) {
		id = entity.getId();
		instantMoment = entity.getInstantMoment();
		costCenter = entity.getCostCenter();
		user = entity.getUser();
		products = entity.getTransactions();
		total = totalValueInput(entity);
	}

	private BigDecimal totalValueInput(InOperation obj) {
		BigDecimal total = BigDecimal.ZERO;
		for (TransactionDTO p : obj.getTransactions()) {
			total = total.add(p.getTotal());
		}
		return total;
	}

}
