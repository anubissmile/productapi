package com.pandasoft.productapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pandasoft.productapi.entity.Product;
import com.pandasoft.productapi.pojo.ResponseMessage;
import com.pandasoft.productapi.service.ProductService;
import com.pandasoft.productapi.service.UserService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;

@RestController
@RequestMapping("/v1/product")
@CrossOrigin(origins = "*")
public class ProductController {
	
	@Autowired
	private ProductService service;
	
	@Autowired
	private UserService userService;
	
	private ResponseMessage msg = new ResponseMessage();
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody Product product, @RequestHeader("token") String token) {
		try {
			if(userService.verifyToken(token)) {
				return service.create(product);
			}
		} catch (Exception e) {
			msg.setMessage(String.format("Verify token failure : %s", e.getMessage()));
			System.out.println(msg.getMessage());
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(msg);
		}
		msg.setMessage("User not found!");
		System.out.println(msg.getMessage());
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(msg);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> save(@PathVariable("id")Long id, @RequestBody Product product, @RequestHeader("token") String token) {
		try {
			if(userService.verifyToken(token)) {
				product.setId(id);
				return service.save(product);
			}
		} catch (Exception e) {
			msg.setMessage(String.format("Verify token failure : %s", e.getMessage()));
			System.out.println(msg.getMessage());
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(msg);
		}
		msg.setMessage("User not found!");
		System.out.println(msg.getMessage());
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(msg);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable("id")Long id, @RequestHeader("token") String token){
		try {
			if(userService.verifyToken(token)) {
				return service.deleteById(id);
			}
		} catch (Exception e) {
			msg.setMessage(String.format("Verify token failure : %s", e.getMessage()));
			System.out.println(msg.getMessage());
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(msg);
		}
		msg.setMessage("User not found!");
		System.out.println(msg.getMessage());
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(msg);
	}
	
	@GetMapping
	public ResponseEntity<?> all(@RequestHeader("token") String token) {
		try {
			if(userService.verifyToken(token)) {
				return service.all();
			}
		} catch (Exception e) {
			msg.setMessage(String.format("Verify token failure : %s", e.getMessage()));
			System.out.println(msg.getMessage());
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(msg);
		}
		msg.setMessage("User not found!");
		System.out.println(msg.getMessage());
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(msg);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable("id")Long id, @RequestHeader("token") String token){
		try {
			if(userService.verifyToken(token)) {
				return service.findById(id);
			}
		} catch (Exception e) {
			msg.setMessage(String.format("Verify token failure : %s", e.getMessage()));
			System.out.println(msg.getMessage());
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(msg);
		}
		msg.setMessage("User not found!");
		System.out.println(msg.getMessage());
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(msg);
	}
	
	

}
