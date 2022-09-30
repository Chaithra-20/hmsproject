package com.thejail.daoImpl;

import com.thejail.config.HibernateUtil;
import com.thejail.dao.thejaildao;
import com.thejail.exception.GlobalException;
import com.thejail.model.user;

import org.hibernate.Session;


public class thejaildaoImpl implements thejaildao{

	@Override
	public int registration(user u1) throws GlobalException  {
		//auto closable session
		try(Session ses=HibernateUtil.getSession()){
			
			ses.beginTransaction();
			//getting username to check availability of username
			String username=u1.getUserName();
			user u2=null;
			//checking username whether existed or not
			u2=(user)ses.createQuery("from user where userName=:username").setParameter("username",username).uniqueResult();
			if(u2==null)
			{
				//saving the user, if username is unique
				ses.save(u1);
				ses.getTransaction().commit();
				return 1;
			}
			else {
				throw new GlobalException("Username already taken");
				
			}
		    }
	}

	@Override
	public user login(String username, String password) throws GlobalException {
		// auto closable session
		try(Session ses=HibernateUtil.getSession()){
			
			ses.beginTransaction();
			//retrievig user details based on entered username
			user u2=(user)ses.createQuery("from user where userName=:username").setParameter("username", username).uniqueResult();
			
			if(u2!=null) {
				
				if(u2.getUserPassword().equals(password)) {
					return u2;
				}
				else {
					throw new GlobalException("Wrong username or password");
					
				}
			}
			else {
				throw new GlobalException("user not found");
			}
		}
		
	}

}
