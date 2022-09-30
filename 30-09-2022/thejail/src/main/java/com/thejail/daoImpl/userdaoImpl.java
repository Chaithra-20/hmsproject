package com.thejail.daoImpl;

import com.thejail.config.HibernateUtil;
import com.thejail.dao.userdao;
import com.thejail.model.user;

import org.hibernate.Session;

public class userdaoImpl implements userdao {

	//getting profile using userId(PK)
	@Override
	public user viewProfile(int uId) {
		
		try(Session ses=HibernateUtil.getSession()){
			
			user u1=null;
			u1=ses.get(user.class,uId);
			return u1;
		}
	}
	
	//retrieving due amount and typecasting result with int
	@Override
	public int viewDueAmount(int uId) {
		try(Session ses=HibernateUtil.getSession()){
			
			int userFee=(int)ses.createQuery("select userFee from user where userId=:uId").setParameter("uId",uId).uniqueResult();
			return userFee;
		}
	}
 
	//viewing room also needs user because roomId is PK in room and fk in user
	@Override
	public user viewRoom(int uId) {
		try(Session ses=HibernateUtil.getSession()){
			
			user u1=null;
			u1=ses.get(user.class,uId);
			return u1;
		}
	}

	@Override
	public int updatePhone(int uId, String Phone) {
		try(Session ses=HibernateUtil.getSession()){
			
		ses.beginTransaction();
		int st=ses.createQuery("update user set userPhone=:phone where userId=:uId").setParameter("phone",Phone).setParameter("uId",uId).executeUpdate();
	
		ses.getTransaction().commit();
		return st;
		}

	}
	
	//changing password only if current password is correct
	@Override
	public int changePassword(int uId, String oldPwd, String newPwd) {
		try(Session ses=HibernateUtil.getSession()){
			
			ses.beginTransaction();
			
			String s1=(String)ses.createQuery("select userPassword from user where userId=:uid").setParameter("uid",uId).uniqueResult();
			
			if(s1.equals(oldPwd)){
				
				int status=ses.createQuery("update user set userPassword=:newpwd where userId=:uid").setParameter("newpwd", newPwd).setParameter("uid",uId).executeUpdate();	
				ses.getTransaction().commit();
				return status;
			}
			else {
				return -1;
			}
		}
	}

}
