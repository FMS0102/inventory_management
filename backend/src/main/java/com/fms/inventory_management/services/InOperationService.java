package com.fms.inventory_management.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fms.inventory_management.dto.InOperationDTO;
import com.fms.inventory_management.dto.TransactionDTO;
import com.fms.inventory_management.entities.CurrentInventory;
import com.fms.inventory_management.entities.InOperation;
import com.fms.inventory_management.repositories.CurrentInventoryRepository;
import com.fms.inventory_management.repositories.InOperationRepository;
import com.fms.inventory_management.services.exceptions.ObjectNotFoundException;

@Service
public class InOperationService {

	@Autowired
	private InOperationRepository repository;

	public List<InOperation> findAll() {
		return repository.findAll();
	}

	public InOperation findById(String id) {
		Optional<InOperation> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}

	public InOperation insert(InOperation obj) {
		return repository.insert(obj);
	}

	public InOperation fromDTO(InOperationDTO objDto) {
		return new InOperation(objDto.getId(), objDto.getInstantMoment(), objDto.getCostCenter(), objDto.getUser());
	}

}
