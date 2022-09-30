package com.thejail;

import java.util.Scanner;

import com.thejail.exception.GlobalException;
import com.thejail.service.loginregister;
import com.thejail.serviceImpl.loginregisterImpl;

import org.apache.log4j.Logger;


public class App 
{
	static Logger log=Logger.getLogger(App.class);
    public static void main( String[] args ) throws GlobalException
    {
    	Scanner ck=new Scanner(System.in);
		log.info( "\t\t----------The Jail------------\t\t" );
        log.info("Press 1 for Registration \n Press 2 for login");
        int op=ck.nextInt();
        loginregister logreg=new loginregisterImpl();
        switch(op) {
        case 1->logreg.registration();
        case 2->logreg.login();
        }
    }
}
