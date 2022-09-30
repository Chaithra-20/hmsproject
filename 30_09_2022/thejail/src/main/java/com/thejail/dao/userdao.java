package com.thejail.dao;

import com.thejail.model.user;

public interface userdao {
	
	public user viewProfile(int uId);
	public int viewDueAmount(int uId);
	public user viewRoom(int uId);
	public int updatePhone(int uId,String Phone);
	public int changePassword(int uid,String oldPwd,String newPwd);

}
