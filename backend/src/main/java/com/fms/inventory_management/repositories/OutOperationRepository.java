package com.fms.inventory_management.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.fms.inventory_management.entities.OutOperation;

@Repository
public interface OutOperationRepository extends MongoRepository<OutOperation, String> {

}
