package com.pandasoft.productapi.pojo;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class ResponseMessage implements Serializable{
	
	private Date timestamp = new Date();
	private String message;
	
}
