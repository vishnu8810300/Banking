package com.javafiles;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyListener implements ServletContextListener {
	
	public  void contextInitialized(ServletContextEvent e)
    {
       try
        {

           ServletContext ctx = e.getServletContext();
          Class.forName("com.mysql.cj.jdbc.Driver"); 
          Connection c =  DriverManager.getConnection("jdbc:mysql://localhost:3306/banksystem","root","root");

        ctx.setAttribute("con",c);
	System.out.println("context created");
        }
        catch(Exception e1)
        {
          }
         }
             public void contextDestroyed(ServletContextEvent e)
        {
      }

}
