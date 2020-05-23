package com.pandasoft.productapi.pojo;

import java.io.Serializable;

import lombok.Data;

@Data
public class ResponseMessageJWTToken extends ResponseMessage implements Serializable {
	
	private String token;

	public ResponseMessageJWTToken(String token) {
		super();
		this.token = token;
	}

	public ResponseMessageJWTToken() {
		super();
	}
}
