package com.javafiles;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

public class ChangePassword extends HttpServlet{
	
	
	public void service(HttpServletRequest req , HttpServletResponse res)throws IOException , ServletException{
	    res.setContentType("text/html");
		PrintWriter out=res.getWriter();
		String pass3=req.getParameter("passname3");
		String pass1=req.getParameter("passname1");
	    String pass2=req.getParameter("passname2");

	    java.sql.Connection c;
		try{
			Class.forName("com.mysql.cj.jdbc.Driver"); 
            c =   DriverManager.getConnection("jdbc:mysql://localhost:3306/bankdata","root","root");
                        Statement s = c.createStatement();
			
			if(pass1.equals(pass2)){
			s.executeUpdate("update employee set password='"+pass1+"' where password='"+pass3+"'");
			RequestDispatcher disp=req.getRequestDispatcher("Home.html");
			out.println("password updated login again");
			disp.include(req,res);
			
			}
			else{
				JOptionPane.showMessageDialog(null,"passwords donot match","Alert message",JOptionPane.WARNING_MESSAGE);
					RequestDispatcher dispatcher=req.getRequestDispatcher("Changepassword.html");
					dispatcher.forward(req,res);
			}
		}
		catch(Exception e){}
	
	}

}
