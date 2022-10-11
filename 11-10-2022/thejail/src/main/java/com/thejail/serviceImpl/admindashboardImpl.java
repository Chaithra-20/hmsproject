package com.thejail.serviceImpl;

import java.util.List;
import java.util.Scanner;

import com.thejail.dao.admindao;
import com.thejail.daoImpl.admindaoImpl;
import com.thejail.exception.GlobalException;
import com.thejail.service.admindashboard;
import com.thejail.model.room;
import com.thejail.model.user;

import org.apache.log4j.Logger;

public class admindashboardImpl implements admindashboard {

	static Logger log=Logger.getLogger(userdashboardImpl.class);
	static Scanner ck=new Scanner(System.in);
	static admindashboardImpl service=new admindashboardImpl();
	static admindao dao=new admindaoImpl();
	
	
	@Override
	public void dashboard()throws GlobalException {
		
		log.info("\t\t---------------Welcome to Admin Dashboard-----------------");
		
		int op=0;
		while(op<10)
		{
			log.info("\t Press 1 for view rooms\t\t Press 2 for view users\n\t Press 3 for create room\t\t Press 4 for allot room\n\t Press 5 for addDue Amount \t\t Press 6 for payDue Amount\n\t Press 7 for view user Profile\t\t Press 8 for delete user\n\t Press 9 for view users in a room");
			op=ck.nextInt();
			
			switch(op) {
				
				case 1->service.viewRooms();
				case 2->service.viewUsers();
				case 3->service.createRoom();
				case 4->service.allotRoom();
				case 5->service.addDueAmount();
				case 6->service.payDueAmount();
				case 7->service.viewUserProfile();
				case 8->service.deleteUser();
				case 9->service.userInRoom();
				default->System.exit(0);
			}
		
		}
		
	}

	@Override
	public void viewRooms() {
		
		List<room> roomList=dao.viewRooms();
		for(room r:roomList) {
			log.info(r);
		}
		
	}

	@Override
	public void viewUsers() {
		
		List<user> userList=dao.viewUsers();
		for(user u1:userList)
			log.info(u1);
	}

	@Override
	public void createRoom() throws GlobalException {
		
		log.info("Enter room Id");
		int rId=ck.nextInt();
		log.info("Enter roomName");
		String rName=ck.next();
		log.info("Enter roomType");
		String rType=ck.next();
		
		room r1=new room();
		r1.setRoomId(rId);
		r1.setRoomName(rName);
		r1.setRoomType(rType);
		
		int st=dao.createRoom(r1);
		if(st==1) {
			log.info("Room added succesfully");
			
		}
	}

	@Override
	public void allotRoom() {
		
		log.info("Enter userId" );
		int uid=ck.nextInt();
		log.info("Enter roomId");
		int rid=ck.nextInt();
		int st=dao.allotRoom(uid, rid);
		if(st==1) {
			log.info("room allocated!....");
		}
	}

	@Override
	public void addDueAmount() {
		
		log.info("Enter user id");
		int uid=ck.nextInt();
		log.info("Enter amount to add");
		int amount=ck.nextInt();
		int st=dao.addDueAmount(uid,amount);
		if(st==1) {
			log.info("fee updated");
			
		}
	}

	@Override
	public void payDueAmount() {
		
		log.info("Enter user id");
		int uid=ck.nextInt();
		log.info("Enter amount to add");
		int amount=ck.nextInt();
		int st=dao.payDueAmount(uid,amount);
		if(st==1) {
			log.info("fee updated");
		}
	}

	@Override
	public void deleteUser() {
		
		log.info("Enter user id");
		int uid=ck.nextInt();
		int st=dao.deleteUser(uid);
		if(st==1){
			log.info("deleted!...");
			
		}
	}

	@Override
	public void userInRoom() {
		
		log.info("Enter room id");
		int rid=ck.nextInt();
		List<user> userList=dao.userInRoom(rid);
		for(user u1:userList)
			log.info(u1);
	}

	@Override
	public void viewUserProfile() {
		log.info("Enter userId" );
		int uid=ck.nextInt();
		user u1=dao.viewUserProfile(uid);
		log.info(u1);;
	}
}

	
