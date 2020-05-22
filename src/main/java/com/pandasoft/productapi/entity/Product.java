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
@Table(name = "res_products")
public class Product implements Serializable {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", length = 7)
	private Long id;
	
	@Column(name = "name", length = 50)
	private String name;
	
	@JsonProperty(value = "product_code")
	@Column(name = "product_code", length = 50)
	private String productCode;
	
	@Column(precision = 7, scale = 2, columnDefinition = "DECIMAL(7, 2)")
	private Double price = 0.0;
	
	@Column(name = "description", length = 255)
	private String description;
	
	@JsonProperty(value = "create_date")
	@CreationTimestamp
	private Date createDate;
	
	@JsonProperty(value = "update_date")
	@UpdateTimestamp
	private Date updateDate;
}
