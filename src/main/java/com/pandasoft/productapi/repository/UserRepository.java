package com.pandasoft.productapi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pandasoft.productapi.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
	
	public Optional<User> findByUsername(String username);
	
}
