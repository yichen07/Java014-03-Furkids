package _00_Init.web;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import _00_Init.util.GlobalService;



@WebListener
public class SystemlnitListener implements ServletContextListener {

    public void contextDestroyed(ServletContextEvent sce)  { 
    	
    }

    public void contextInitialized(ServletContextEvent sce)  { 
    	ServletContext ctx = sce.getServletContext();
//    	GlobalService service = new GlobalService();
    	ctx.setAttribute("SYSTEM", new GlobalService());
    }
	
}
