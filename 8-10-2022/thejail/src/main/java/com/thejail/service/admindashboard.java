package com.thejail.service;

import java.util.List;

import com.thejail.exception.GlobalException;
import com.thejail.model.user;
import com.thejail.model.room;

public interface admindashboard {

	public void dashboard()throws GlobalException;
	public void viewRooms();
	public void viewUsers();
	public void createRoom() throws GlobalException;
	public void allotRoom();
	public void addDueAmount();
	public void payDueAmount();
	public void deleteUser();
	public void userInRoom();
	public void viewUserProfile();

}
