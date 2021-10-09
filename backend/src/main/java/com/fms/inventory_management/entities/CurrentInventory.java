package com.fms.inventory_management.entities;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "inventory")
public class CurrentInventory extends InOperation implements Serializable {
	private static final long serialVersionUID = 1L;

}
