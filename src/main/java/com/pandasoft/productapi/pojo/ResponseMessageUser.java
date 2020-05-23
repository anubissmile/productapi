package com.pandasoft.productapi.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.pandasoft.productapi.entity.User;

import lombok.Data;

@Data
public class ResponseMessageUser extends ResponseMessage implements Serializable {
	
	private List<User> datas;

	public ResponseMessageUser(List<User> datas) {
		super();
		this.datas = datas;
	}

	public ResponseMessageUser(User data) {
		super();
		datas = new ArrayList<User>();
		datas.add(data);
	}

	public ResponseMessageUser() {
		super();
	}
}
