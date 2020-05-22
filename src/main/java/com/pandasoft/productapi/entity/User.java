package com.pandasoft.productapi.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@Entity
@Table(name = "res_user")
public class User implements Serializable {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", length = 7)
	private Long id;
	
	@Column(length = 50, name = "name")
	private String name;
	
	@Column(length = 30, name = "username")
	private String username;
	
	@Column(length = 500, name = "password")
	private String password;
	
	@CreationTimestamp
	@JsonProperty(value = "create_date")
	private Date createDate;
	
	@UpdateTimestamp
	@JsonProperty(value = "update_date")
	private Date updateDate;

}
