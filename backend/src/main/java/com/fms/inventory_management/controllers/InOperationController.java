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

import com.fms.inventory_management.dto.InOperationDTO;
import com.fms.inventory_management.entities.InOperation;
import com.fms.inventory_management.services.InOperationService;

@RestController
@RequestMapping(value = "/inputs")
public class InOperationController {

	@Autowired
	private InOperationService service;

	@GetMapping
	public ResponseEntity<List<InOperationDTO>> findAll() {
		List<InOperation> list = service.findAll();
		List<InOperationDTO> listDTO = list.stream().map(x -> new InOperationDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<InOperationDTO> findById(@PathVariable String id) {
		InOperation obj = service.findById(id);
		return ResponseEntity.ok().body(new InOperationDTO(obj));
	}

//	@GetMapping(value = "/codprod")
//	public ResponseEntity<InOperationDTO> findByCodProd(
//			@RequestParam(value = "text", defaultValue = "") Long codProd) {
//		InOperation obj = service.findByCodProd(codProd);
//		return ResponseEntity.ok().body(new InOperationDTO(obj));
//	}
//
//	@GetMapping(value = "/materialtype")
//	public ResponseEntity<List<InOperation>> findByMaterialType(
//			@RequestParam(value = "text", defaultValue = "") String materialType) {
//		List<InOperation> list = service.findByMaterialType(materialType);
//		return ResponseEntity.ok().body(list);
//	}

	@PostMapping
	public ResponseEntity<Void> insert(@RequestBody InOperationDTO objDto) {
		InOperation obj = service.fromDTO(objDto);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
}
