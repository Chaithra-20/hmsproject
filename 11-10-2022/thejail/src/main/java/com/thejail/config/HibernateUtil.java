package com.thejail.config;

import java.util.Properties;

import com.thejail.model.room;
import com.thejail.model.user;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

public class HibernateUtil {

	private static SessionFactory sesFact;
	
	private static SessionFactory getSessionFactory() {
	
		if(sesFact==null) {
			try
			{
				
				Configuration conf=new Configuration();
				Properties pro=new Properties();
				pro.put(Environment.DRIVER,"com.mysql.cj.jdbc.Driver");
				pro.put(Environment.URL, "jdbc:mysql://localhost:3306/thejail");
				pro.put(Environment.USER,"root");
				pro.put(Environment.PASS,"789910");
				pro.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
				pro.put(Environment.HBM2DDL_AUTO,"update");
				conf.setProperties(pro);
				conf.addAnnotatedClass(user.class);
				conf.addAnnotatedClass(room.class);
				sesFact=conf.buildSessionFactory();
				
			}
			catch(Exception e) {
				
			}
		
		}
		return sesFact;
	}
	public static Session getSession() {
		return getSessionFactory().openSession();
		
	}
}
