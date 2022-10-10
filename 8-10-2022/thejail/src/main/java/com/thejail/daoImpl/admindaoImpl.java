package com.thejail.daoImpl;

import java.util.List;

import com.thejail.config.HibernateUtil;
import com.thejail.dao.admindao;
import com.thejail.exception.GlobalException;
import com.thejail.model.room;
import com.thejail.model.user;

import org.hibernate.Session;

public class admindaoImpl implements admindao {

	@Override
	public List<room> viewRooms() {
		
		try(Session ses=HibernateUtil.getSession()){
			
			List<room> roomList=ses.createQuery("from room").getResultList();
			return roomList;
		}
	}

	@Override
	public List<user> viewUsers() {
		
		try(Session ses=HibernateUtil.getSession()){
			
			List<user> userList=ses.createQuery("from user where userRole='student'").getResultList();
			return userList;
		}
	}

	@Override
	public int createRoom(room r1) throws GlobalException {

		String roomname=r1.getRoomName();
		try(Session ses=HibernateUtil.getSession()){
			ses.beginTransaction();
			room r2=(room)ses.createQuery("from room where roomName=:roomname").setParameter("roomname",roomname).uniqueResult();
			
			if(r2!=null) {
				throw new GlobalException("Room name already existed");
			}
			else {
				ses.save(r1);
				ses.getTransaction().commit();
				return 1;
			}
		}
	}

	@Override
	public int allotRoom(int uId, int rId) {
	
		try(Session ses=HibernateUtil.getSession()){
			ses.beginTransaction();
			int status=ses.createQuery("update user set userRoom_roomId=:roomid where userId=:userid").setParameter("roomid",rId).setParameter("userid", uId).executeUpdate();
			ses.getTransaction().commit();
			return status;
			 
		}
	}

	@Override
	public int addDueAmount(int uId, int amount) {
		
		try(Session ses=HibernateUtil.getSession())
		{
			ses.beginTransaction();
			user u2=ses.get(user.class, uId);
			
			int dueAmount=u2.getUserFee();
			dueAmount+=amount;
			int status=ses.createQuery("update user set userFee=:amount where userId=:uid").setParameter("amount",dueAmount).setParameter("uid",uId).executeUpdate();
			ses.getTransaction().commit();
			return status;
		}
	}

	@Override
	public int payDueAmount(int uId, int amount) {
			
		try(Session ses=HibernateUtil.getSession())
		{
			ses.beginTransaction();
			
			user u2=ses.get(user.class,uId);
			int dueAmount=u2.getUserFee();
			dueAmount-=amount;
			int status=ses.createQuery("update user set userFee=:amount where userID=:uid").setParameter("amount",dueAmount).setParameter("uid",uId).executeUpdate();
			ses.getTransaction().commit();
			return status;
		}
	}

	@Override
	public int deleteUser(int uId) {
			
		try(Session ses=HibernateUtil.getSession()){
			ses.beginTransaction();
			int status=ses.createQuery("delete from user where userId=:uid").setParameter("uid", uId).executeUpdate();
			ses.getTransaction().commit();
			return status;
		}
	}

	@Override
	public List<user> userInRoom(int rId) {
		try(Session ses=HibernateUtil.getSession()){
			
			List<user> userList=ses.createQuery("from user where userRoom_roomId=:rid").setParameter("rid", rId).getResultList();
			return userList;
		}
	}

	@Override
	public user viewUserProfile(int uId) {

		try(Session ses=HibernateUtil.getSession()){
			
			user u2=ses.get(user.class, uId);
			return u2;
		}
	}

}
