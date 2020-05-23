package com.pandasoft.productapi.service;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.pandasoft.productapi.entity.User;
import com.pandasoft.productapi.helper.AuthHelper;
import com.pandasoft.productapi.pojo.ResponseMessageJWTToken;
import com.pandasoft.productapi.pojo.ResponseMessageUser;
import com.pandasoft.productapi.properties.SecuritiesProperties;
import com.pandasoft.productapi.repository.UserRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.TextCodec;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repo;
	
	@Autowired
	private SecuritiesProperties secProp;
	
	private ResponseMessageUser msg = new ResponseMessageUser();
	
	private ResponseMessageJWTToken msgToken = new ResponseMessageJWTToken();
	
	/**
	 * Create user
	 * @param User user
	 * @return ResponseEntity<?>
	 */
	public ResponseEntity<?> create(User user){
		ResponseMessageUser msg = new ResponseMessageUser();

		/**
		 * Checking username.
		 */
		if(!repo.findByUsername(user.getUsername()).isPresent()) {
			
			/**
			 * Hashing password
			 */
			user.setPassword(AuthHelper.bcrypt(secProp.STRENGTH, secProp.SALT + user.getPassword()));
			
			/**
			 * Saving data.
			 */
			user.setId(null);
			User result = repo.save(user);
			if(result.getId() != null) {
				result.setPassword("*****");
				msg = new ResponseMessageUser(result);
				msg.setMessage("Create user successfully!");
				return ResponseEntity.status(HttpStatus.CREATED).body(msg);
			}
			msg.setMessage("Create user has failure!");
			return ResponseEntity.badRequest().body(msg);
		} else {
			msg.setMessage("Username is already exists!");
			return ResponseEntity.badRequest().body(msg);
		}
	}
	
	public ResponseEntity<?> authByUsername(User user){
		ResponseMessageUser msg = new ResponseMessageUser();
		
		Optional<User> users = repo.findByUsername(user.getUsername());
		if(users.isPresent()) {
			User result = users.get();
			
			/**
			 * Verify password.
			 */
			if(AuthHelper.verifyPassword(secProp.SALT + user.getPassword(), result.getPassword())) {
				
				String token = Jwts.builder()
						.setIssuer("Wesarut")
						.setIssuedAt(new Date())
						.setSubject("Authorization.")
						.setId(AuthHelper.bcrypt(7, new Date().toString() + "Jti Product api"))
						.claim("username", result.getUsername())
						.claim("user_id", result.getId())
						.signWith(
							SignatureAlgorithm.HS256, 
							secProp.JWT_SIGNING_KEY)
						.compact();
				
				msgToken.setToken(token);
				msgToken.setMessage("Authorization successfully!");
				return ResponseEntity.ok(msgToken);
				
			} else {
				msg.setMessage("Your password went wrong!");
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(msg);
			}
		} else {
			msg.setMessage("User not found!");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(msg);
		}
	}
	
	public Boolean verifyToken(String token) throws Exception {
		try {
			Jws<Claims> jws = Jwts.parser()
					.setSigningKey(secProp.JWT_SIGNING_KEY)
			        .parseClaimsJws(token);
			Long userId = Long.valueOf(jws.getBody().get("user_id").toString());
			String username = jws.getBody().get("username").toString();
			Optional<User> userOpt = repo.findById(userId);
			if(userOpt.isPresent()) {
				if(username.equals(userOpt.get().getUsername())) {
					return true;
				}
			}
			return false;
		} catch (Exception e) {
			throw new Exception(String.format("JWT verifying failure! : %s", e.getMessage()));
		}
		
	}

}
