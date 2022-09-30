package com.thejail.service;

import com.thejail.exception.GlobalException;
import com.thejail.model.user;

public interface userdashboard {
		
	public void dashboard(int uId)throws GlobalException;
	public void viewProfile();
	public void viewDueAmount();
	public void viewRoom();
	public void updatePhone()throws GlobalException;
	public void changePassword()throws GlobalException;
}
