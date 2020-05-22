package com.pandasoft.productapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pandasoft.productapi.entity.Product;
import com.pandasoft.productapi.service.ProductService;

@RestController
@RequestMapping("/v1/product")
@CrossOrigin(origins = "*")
public class ProductController {
	
	@Autowired
	private ProductService service;
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody Product product) {
		return service.create(product);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> save(@PathVariable("id")Long id, @RequestBody Product product) {
		product.setId(id);
		return service.save(product);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable("id")Long id){
		return service.deleteById(id);
	}
	
	@GetMapping
	public ResponseEntity<?> all() {
		return service.all();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable("id")Long id){
		return service.findById(id);
	}
	
	

}
