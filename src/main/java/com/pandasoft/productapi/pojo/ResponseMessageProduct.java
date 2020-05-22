package com.pandasoft.productapi.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.pandasoft.productapi.entity.Product;

import lombok.Data;

@Data
public class ResponseMessageProduct extends ResponseMessage implements Serializable {
	
	private List<Product> datas;

	public ResponseMessageProduct(List<Product> datas) {
		super();
		this.datas = datas;
	}
	
	public ResponseMessageProduct(Product data) {
		super();
		datas = new ArrayList<Product>();
		datas.add(data);
	}

	public ResponseMessageProduct() {
		super();
	}

}
