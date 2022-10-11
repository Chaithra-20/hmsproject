package com.thejail.dao;

import java.util.List;

import com.thejail.exception.GlobalException;
import com.thejail.model.room;
import com.thejail.model.user;

public interface admindao {
	
	public List<room> viewRooms();
	public List<user> viewUsers();
	public int createRoom(room r1) throws GlobalException;
	public int allotRoom(int uId,int rId);
	public int addDueAmount(int uId,int amount);
	public int payDueAmount(int uId,int amount);
	public int deleteUser(int uId);
	public List<user> userInRoom(int rId);
	public user viewUserProfile(int uId);
	
}
