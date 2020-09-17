package _00_Init.util.web;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import _00_Init.util.utils.HibernateUtils;



//@WebListener
public class CreateSessionFactoryListener implements ServletContextListener {

    public void contextDestroyed(ServletContextEvent sce)  { 
    	HibernateUtils.close();
    }


    public void contextInitialized(ServletContextEvent sce)  { 
    	HibernateUtils.getSessionFactory();
    }
	
}
