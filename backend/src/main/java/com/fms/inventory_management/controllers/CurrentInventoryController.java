package com.fms.inventory_management.controllers;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fms.inventory_management.dto.CurrentInventoryDTO;
import com.fms.inventory_management.entities.CurrentInventory;
import com.fms.inventory_management.services.CurrentInventoryService;

@RestController
@RequestMapping(value = "/inventory")
public class CurrentInventoryController {

	@Autowired
	private CurrentInventoryService service;

	@GetMapping
	public ResponseEntity<List<CurrentInventoryDTO>> findAll() {
		List<CurrentInventory> list = service.findAll();
		List<CurrentInventoryDTO> listDTO = list.stream().map(x -> new CurrentInventoryDTO(x))
				.collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<CurrentInventoryDTO> findById(@PathVariable String id) {
		CurrentInventory obj = service.findById(id);
		return ResponseEntity.ok().body(new CurrentInventoryDTO(obj));
	}

	@PostMapping
	public ResponseEntity<Void> insert(@RequestBody CurrentInventoryDTO objDto) {
		CurrentInventory obj = service.fromDTO(objDto);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getProdCod())
				.toUri();
		return ResponseEntity.created(uri).build();
	}
}
