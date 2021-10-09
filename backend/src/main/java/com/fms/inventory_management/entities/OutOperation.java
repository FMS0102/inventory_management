package com.fms.inventory_management.entities;

import java.io.Serializable;
import java.time.Instant;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Data
@Document(collection = "outputs")
public class OutOperation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	private Instant instantMoment;
	private String costApplication;
	private User user;
}
