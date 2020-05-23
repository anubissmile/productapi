package com.pandasoft.productapi.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pandasoft.productapi.entity.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
	
	public Optional<Product> findByProductCode(String productCode);
	
}
