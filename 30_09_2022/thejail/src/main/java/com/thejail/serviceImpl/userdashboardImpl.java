package com.thejail.serviceImpl;

import java.util.Scanner;

import com.thejail.dao.userdao;
import com.thejail.daoImpl.userdaoImpl;
import com.thejail.exception.GlobalException;
import com.thejail.model.room;
import com.thejail.model.user;
import com.thejail.service.userdashboard;

import org.apache.log4j.Logger;

public class userdashboardImpl implements userdashboard {
	
	static Logger log=Logger.getLogger(userdashboardImpl.class);
	
	static int userId=0;
	static Scanner ck=new Scanner(System.in);
	static userdashboard ud1=new userdashboardImpl();
	static userdao dao=new userdaoImpl();

	@Override
	public void dashboard(int uId) throws GlobalException {

		log.info("\t\t--------------Welcome to user dashboard---------------");
		
		userId=uId;
		int op=0;
		//selection of operation
		while(op<6)
		{
			
		log.info("\n\t Press 1 for view profile \t\t Press 2 for view Due Amount\n \t Press 3 for view Room\t\t Press 4 for update Phone number\n\t Press 5 for change Password");
		op=ck.nextInt();
		
		switch(op) {
			
			case 1->ud1.viewProfile();
			
			case 2->ud1.viewDueAmount();
			
			case 3->ud1.viewRoom();
			
			case 4->ud1.updatePhone();
			
			case 5->ud1.changePassword();
		}
			
		}
	}

	//view profile
	@Override
	public void viewProfile() {
		// calling dao viewprofile
		user u1=dao.viewProfile(userId);
		log.info(u1);
		
	}

	//view fee Details
	@Override
	public void viewDueAmount() {
		
		int amount=dao.viewDueAmount(userId);
		log.info("Hello User your due amount id "+amount);
		
	}

	//view the rooom
	@Override
	public void viewRoom() {
		
		user u1=dao.viewProfile(userId);
		room r1=u1.getUserRoom();
		log.info("your room number is :"+r1.getRoomId()+" name is :"+r1.getRoomName()+" and it is "+r1.getRoomType()+" room ");
		
	}

	//update Phone number
	@Override
	public void updatePhone() throws GlobalException {
		
		log.info("Enter new Phone number");
		String phone=ck.next();
		int status=dao.updatePhone(userId, phone);
		if(status==1) {
			log.info("phone number updated!...");
			
		}
		else {
			throw new GlobalException("somethhing went wrong");
		}
		
	}

	//to change password, have to enter current password correctly
	@Override
	public void changePassword() throws GlobalException {
		
		log.info("Enter current password");;
		String oldpwd=ck.next();
		log.info("Enter new password");
		String newpwd=ck.next();
		int status=dao.changePassword(userId, oldpwd, newpwd);
		if(status==1) {
			log.info("password updated!...");
		
	}
		else {
			throw new GlobalException("something went wrong");
		}
	

}
}
