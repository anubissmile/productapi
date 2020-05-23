package com.pandasoft.productapi.utils;

import java.util.Date;
import java.util.List;

import com.pandasoft.productapi.entity.User;
import com.pandasoft.productapi.helper.AuthHelper;

public class UserUtils {

	public User verifyPassword(List<User> users, String pwd) {
		
		for(User user : users) {
			boolean chk = AuthHelper.verifyPassword(pwd, user.getPassword());
			if(chk) {
				return user;
			}
		}
		return null;
	}
	
	/**
	 * Checking whether this token got expire.
	 * @param Date expire
	 * @return boolean | Return true if token was expire.
	 */
	public boolean isTokenExpired(Date expire) {
		Date now = new Date();
		return now.after(expire);
	}

}
