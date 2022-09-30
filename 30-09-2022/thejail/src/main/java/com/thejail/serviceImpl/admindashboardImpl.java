package com.thejail.serviceImpl;

import com.thejail.service.admindashboard;

import org.apache.log4j.Logger;

public class admindashboardImpl implements admindashboard {

	static Logger log=Logger.getLogger(userdashboardImpl.class);
	
	@Override
	public void dashboard() {
		
		log.info("\t\t---------------Welcome to Admin Dashboard-----------------");
		
		
	}
}
