package com.javafiles;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;

public class servlet extends HttpServlet implements Servlet{
	
	
	public void service(ServletRequest req,ServletResponse res) throws ServletException, IOException{
		
		res.setContentType("text/html");
		PrintWriter pw=res.getWriter();
		String name=req.getParameter("username");

		String pass=req.getParameter("passname");	
		String email=req.getParameter("emailname");	
		String contact=req.getParameter("contactname");

		 java.sql.Connection c;    	
		try{
			Class.forName("com.mysql.cj.jdbc.Driver"); 
            c =   DriverManager.getConnection("jdbc:mysql://localhost:3306/bankdata","root","root");
			
                        PreparedStatement ps=c.prepareStatement("insert into employee values(?,?,?,?)");
                       
			ps.setString(1,name);
			ps.setString(2,pass);
			ps.setString(3,email);
			ps.setString(4,contact);
			ps.executeUpdate();
			
			RequestDispatcher dispatcher=req.getRequestDispatcher("/Home.html");
			dispatcher.include(req,res);
			pw.println("<br>hey "+name+" you are registered succesfully");
		}
		catch(Exception e){
			
	System.out.println(e);
		}
		pw.close();
	}
	


}
