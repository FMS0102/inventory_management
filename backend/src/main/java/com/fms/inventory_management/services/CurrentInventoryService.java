package com.fms.inventory_management.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fms.inventory_management.dto.CurrentInventoryDTO;
import com.fms.inventory_management.entities.CurrentInventory;
import com.fms.inventory_management.entities.Product;
import com.fms.inventory_management.repositories.CurrentInventoryRepository;
import com.fms.inventory_management.services.exceptions.ObjectNotFoundException;

@Service
public class CurrentInventoryService {

	@Autowired
	private CurrentInventoryRepository repository;

	public List<CurrentInventory> findAll() {
		return repository.findAll();
	}

	public CurrentInventory findById(String id) {
		Optional<CurrentInventory> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}

	public CurrentInventory insert(CurrentInventory obj) {
		return repository.insert(obj);
	}

	public CurrentInventory fromDTO(CurrentInventoryDTO objDto) {
		Product product = new Product(objDto.getProdCod(), objDto.getName(), objDto.getUnitMeasurement(),
				objDto.getPrice(), objDto.getMaterialType());
		return new CurrentInventory(objDto.getQuantity(), objDto.getPrice(), product);
	}

}
