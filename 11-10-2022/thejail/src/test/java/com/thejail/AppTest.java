package com.thejail;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.thejail.dao.admindao;
import com.thejail.dao.thejaildao;
import com.thejail.dao.userdao;
import com.thejail.daoImpl.admindaoImpl;
import com.thejail.daoImpl.thejaildaoImpl;
import com.thejail.daoImpl.userdaoImpl;
import com.thejail.exception.GlobalException;
import com.thejail.model.room;
import com.thejail.model.user;

public class AppTest 
{
	@Test
	
	public void testuserregistration()throws GlobalException{
		user u1=new user();
		u1.setUserName("Bhuvi");
		u1.setUserPassword("bhuvi@07");
		u1.setUserPhone("9837963266");
		u1.setUserAddress("Electronic city");
		u1.setUserRole("student");
		thejaildao dao=new thejaildaoImpl();
				
		assertThrows(GlobalException.class,()->dao.registration(u1));
	}
	@Test
	
	public void testpassword() {
		
		userdao dao=new userdaoImpl();
		
		assertEquals(-1,dao.changePassword(1, "2002", "Thanu@02"));
		
	}
	
	@Test
	public void testroom() throws GlobalException {
		
		admindao dao=new admindaoImpl();
		room r1=new room();
		r1.setRoomId(103);
		r1.setRoomName("Vrushabadri");
		r1.setRoomType("AC");
		
		
		assertThrows(GlobalException.class,()->dao.createRoom(r1));
				
		
		
		
	}
	
	
	
	
}
