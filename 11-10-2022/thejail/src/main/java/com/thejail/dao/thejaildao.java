package com.thejail.dao;

import com.thejail.exception.GlobalException;
import com.thejail.model.user;

public interface thejaildao {

	public int registration(user u1)throws GlobalException;
	public user login(String username,String password)throws GlobalException;
}
