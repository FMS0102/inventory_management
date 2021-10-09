package com.fms.inventory_management.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.fms.inventory_management.entities.Product;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {

	@Query("{'prodCod': {$eq: ?0}}")
	Optional<Product> findByCodProd(Long codProd);

	@Query("{'materialType': {$regex: ?0, $options: 'i'}}")
	Optional<List<Product>> findByMaterialType(String materialType);

}
