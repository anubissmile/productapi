package com.pandasoft.productapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pandasoft.productapi.entity.User;
import com.pandasoft.productapi.pojo.ResponseMessage;
import com.pandasoft.productapi.service.UserService;

@RestController
@RequestMapping("/v1/user")
@CrossOrigin(origins = "*")
public class UserController {
	
	@Autowired
	private UserService service;
	
	private ResponseMessage msg = new ResponseMessage();
	
	@PostMapping("/authorize")
	public ResponseEntity<?> auth(@RequestBody User user){
		return service.authByUsername(user);
	}
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody User user, @RequestHeader("token")String token){
		try {
			if(service.verifyToken(token)) {
				return service.create(user);
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
