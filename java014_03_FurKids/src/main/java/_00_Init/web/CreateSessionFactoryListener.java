package _00_Init.web;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.hibernate.SessionFactory;

import _01_Member.util.HibernateUtils;

@WebListener
public class CreateSessionFactoryListener implements ServletContextListener {
	SessionFactory factory;
	// 靜態區塊在程式被載入時，即會被啟動。
//	static {
//	}
//	static {
//		System.out.println("=========================================");
//	}
//    
	
    public void contextDestroyed(ServletContextEvent sce)  { 
    	HibernateUtils.getSessionFactory().close();
    }

    public void contextInitialized(ServletContextEvent sce)  { 
        HibernateUtils.getSessionFactory();
    }
	
}
