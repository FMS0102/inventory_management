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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fms.inventory_management.dto.ProductDTO;
import com.fms.inventory_management.entities.Product;
import com.fms.inventory_management.services.ProductService;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

	@Autowired
	private ProductService service;

	@GetMapping
	public ResponseEntity<List<ProductDTO>> findAll() {
		List<Product> list = service.findAll();
		List<ProductDTO> listDTO = list.stream().map(x -> new ProductDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<ProductDTO> findById(@PathVariable String id) {
		Product obj = service.findById(id);
		return ResponseEntity.ok().body(new ProductDTO(obj));
	}

	@GetMapping(value = "/codprod")
	public ResponseEntity<ProductDTO> findByCodProd(@RequestParam(value = "text", defaultValue = "") Long codProd) {
		Product obj = service.findByCodProd(codProd);
		return ResponseEntity.ok().body(new ProductDTO(obj));
	}

	@GetMapping(value = "/materialtype")
	public ResponseEntity<List<Product>> findByMaterialType(
			@RequestParam(value = "text", defaultValue = "") String materialType) {
		List<Product> list = service.findByMaterialType(materialType);
		return ResponseEntity.ok().body(list);
	}

	@PostMapping
	public ResponseEntity<Void> insert(@RequestBody ProductDTO objDto) {
		Product obj = service.fromDTO(objDto);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
}
