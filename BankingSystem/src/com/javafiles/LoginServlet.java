package com.javafiles;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

public class LoginServlet extends HttpServlet{
	
	public void service(HttpServletRequest req , HttpServletResponse res)throws IOException , ServletException
	{
		res.setContentType("text/html");
		PrintWriter out=res.getWriter();
        String type=req.getParameter("type");
		String user=req.getParameter("txtname");
		String password=req.getParameter("passname");
		HttpSession session=req.getSession();
		session.setAttribute("name",user);
		
		 java.sql.Connection c;
		try{
			int flag=0;
			Class.forName("com.mysql.cj.jdbc.Driver"); 
            c =   DriverManager.getConnection("jdbc:mysql://localhost:3306/bankdata","root","root");
                 Statement s = c.createStatement();

			if(type.equals("User")){
			    ResultSet rs=s.executeQuery("select name,password from employee");
			
			         while(rs.next()){
				         if(user.equals(rs.getString(1))&& password.equals(rs.getString(2))){
					     out.println("<html> <body align=center>welcome </body></html>" +user);
					     
					 
					     RequestDispatcher dispatcher=req.getRequestDispatcher("AfterLogin.html");
					     dispatcher.include(req,res);
					     flag=1;
					     break;
						 }
					 }
			
				
			  
			
				
				if(flag==0){
					JOptionPane.showMessageDialog(null,"invalid username or password","Alert Message",JOptionPane.WARNING_MESSAGE);
					RequestDispatcher dispatcher=req.getRequestDispatcher("Home.html");
					dispatcher.include(req,res);
				
				
				}
			}
			else{
				
				        if(user.equals("simran")&& password.equals("admin")){
					     out.println("welcome " +user);
					     RequestDispatcher dispatcher1=req.getRequestDispatcher("Accountdetails.html");
					     dispatcher1.forward(req,res);
						
						
						}
						else{
					    JOptionPane.showMessageDialog(null,"invalid username or password","Alert Message",JOptionPane.WARNING_MESSAGE);
					    RequestDispatcher dispatcher=req.getRequestDispatcher("Home.html");
					     dispatcher.include(req,res);
				
				
				}  
				
			}
		}
					
					
		
		catch(Exception e){
			
			System.out.println(e);
		}
			
	
	}

}
