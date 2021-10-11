package com.fms.inventory_management.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fms.inventory_management.dto.ProductDTO;
import com.fms.inventory_management.entities.Product;
import com.fms.inventory_management.repositories.ProductRepository;
import com.fms.inventory_management.services.exceptions.ObjectNotFoundException;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;

	public List<Product> findAll() {
		return repository.findAll();
	}

	public Product findById(String id) {
		Optional<Product> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}

	public Product findByCodProd(Long codProd) {
		Optional<Product> obj = repository.findByCodProd(codProd);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}

	public List<Product> findByMaterialType(String materialType) {
		Optional<List<Product>> obj = repository.findByMaterialType(materialType);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}

	public Product insert(Product obj) {
		return repository.insert(obj);
	}

	public Product fromDTO(ProductDTO objDto) {
		return new Product(objDto.getProdCod(), objDto.getName(), objDto.getUnitMeasurement(), objDto.getPrice(),
				objDto.getMaterialType());
	}
}
