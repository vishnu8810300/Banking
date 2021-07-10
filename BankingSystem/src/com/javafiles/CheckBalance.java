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

public class CheckBalance  extends HttpServlet{
	
	public void service(HttpServletRequest req , HttpServletResponse res)throws IOException , ServletException
	{
		res.setContentType("text/html");
		PrintWriter out=res.getWriter();
		String accountno=req.getParameter("accountno");
		 java.sql.Connection c;

		try{

			Class.forName("com.mysql.cj.jdbc.Driver"); 
            c =   DriverManager.getConnection("jdbc:mysql://localhost:3306/bankdata","root","root");
                        Statement s = c.createStatement();;
			ResultSet rs=s.executeQuery("select balance from accountdata where accountno='"+accountno+"'");
			while(rs.next()){
				out.println(rs.getString(1)+"Rs.");
			}
			RequestDispatcher disp=req.getRequestDispatcher("AfterLogin.html");
				disp.include(req,res);
			
		}
		catch(Exception e){}
	}

}
