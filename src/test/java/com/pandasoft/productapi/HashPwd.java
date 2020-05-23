package com.pandasoft.productapi;

import org.junit.jupiter.api.Test;

import com.pandasoft.productapi.helper.AuthHelper;
import com.pandasoft.productapi.properties.SecuritiesProperties;


public class HashPwd {
	
	@Test
	public void testHashPassword() {
		String pwd = "temp1234";
		SecuritiesProperties sec = new SecuritiesProperties();
		String hashed = AuthHelper.bcrypt(sec.STRENGTH, sec.SALT + pwd);
		System.out.println(hashed);
	}
	

}
