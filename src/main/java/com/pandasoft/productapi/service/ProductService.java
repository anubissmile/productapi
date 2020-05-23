package com.pandasoft.productapi.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.pandasoft.productapi.entity.Product;
import com.pandasoft.productapi.pojo.ResponseMessageProduct;
import com.pandasoft.productapi.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository repo;
	
	private ResponseMessageProduct msgProduct = new ResponseMessageProduct();
	
	public ResponseEntity<?> create(Product product) {
		product.setId(null);
		msgProduct = new ResponseMessageProduct(repo.save(product));
		msgProduct.setMessage("Create new product successfully!");
		return ResponseEntity.status(HttpStatus.CREATED).body(msgProduct);
	}
	
	public ResponseEntity<?> save(Product product) {
		if(product.getId() == null) {
			msgProduct.setMessage("Please specify product id.");
			return ResponseEntity.badRequest().body(msgProduct);
		}
		
		if(!repo.existsById(product.getId())) {
			msgProduct.setMessage("This product item not found!");
			return ResponseEntity.badRequest().body(msgProduct);
		}
		
		msgProduct = new ResponseMessageProduct(repo.save(product));
		msgProduct.setMessage("Save product successfully!");
		return ResponseEntity.ok(msgProduct);
	}
	
	public ResponseEntity<?> deleteById(Long id){
		repo.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	public ResponseEntity<?> findById(Long id){
		Optional<Product> prodOpt = repo.findById(id);
		
		if(prodOpt.isPresent()) {
			msgProduct = new ResponseMessageProduct(prodOpt.get());
			msgProduct.setMessage("Product found!");
			return ResponseEntity.ok(msgProduct);
		} else {
			msgProduct.setMessage("Product not found!");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msgProduct);
		}
	}
	
	public ResponseEntity<?> all() {
		List<Product> prods = (List<Product>) repo.findAll();
		if (!prods.isEmpty()) {
			msgProduct = new ResponseMessageProduct(prods);
			msgProduct.setMessage("Products found!");
			return ResponseEntity.ok(msgProduct);
		} else {
			msgProduct.setMessage("Products not found!");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msgProduct);
		}
	}

}
