package com.thejail.serviceImpl;

import java.util.Scanner;
import java.util.regex.Pattern;

import com.thejail.App;
import com.thejail.dao.thejaildao;
import com.thejail.daoImpl.thejaildaoImpl;
import com.thejail.exception.GlobalException;
import com.thejail.model.user;
import com.thejail.service.admindashboard;
import com.thejail.service.loginregister;
import com.thejail.service.userdashboard;

import org.apache.log4j.Logger;


public class loginregisterImpl implements loginregister  {
	static Logger log=Logger.getLogger(App.class);
	static thejaildao dao=new thejaildaoImpl();
	static Scanner ck=new Scanner(System.in);

	@Override
	//registration process
	public void registration() throws GlobalException {
		
		log.info("Welcome to registration");
		user u1=new user();
		log.info("Enter Username");
		String name=ck.next();
		log.info("Create password");
		String pwd=ck.next();
		log.info("Enter Phone");
		String phone=ck.next();
		log.info("Enter Address");
		String adr=ck.next();
		
		u1.setUserName(name);
		u1.setUserAddress(adr);
		u1.setUserPhone(phone);
		u1.setUserPassword(pwd);
		u1.setUserRole("student");
		u1.setUserFee(0);
		u1.setUserRoom(null);
		//validating user data
		if(Pattern.matches("[a-zA-Z]{4,}",name)&&Pattern.matches("[0-9]{10}",phone)&&Pattern.matches("[a-zA-Z0-9@#]{6,}",pwd));
		{
				//inserting user data
		    int status=dao.registration(u1);
		    if(status==1)
		    {
				log.info("registration success");
			}
		
		    else {
			throw new GlobalException("Invalid data");
		    }
	}
	
	}
	
	@Override
	public void login() throws GlobalException {
		
		log.info("Enter username");
		String uname=ck.next();
		log.info("Enter Password");
		String upwd=ck.next();
		//calling dao login method
		user u1=dao.login(uname,upwd);
		userdashboard ud1=new userdashboardImpl();
		admindashboard ad1=new admindashboardImpl();
		
		if(u1!=null) {
			log.info("Hey"+u1.getUserName()+"login success");
			
			if(u1.getUserRole().equals("student")) {
				ud1.dashboard(u1.getUserId());
			}
			else if(u1.getUserRole().equals("admin")) {
				ad1.dashboard();
			}
		}
		

	}

}

	