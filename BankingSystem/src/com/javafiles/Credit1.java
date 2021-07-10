package com.javafiles;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Credit1  extends HttpServlet{
	
	public void service(HttpServletRequest req , HttpServletResponse res)throws IOException , ServletException
	{
		res.setContentType("text/html");
		PrintWriter out=res.getWriter();
		String accountno=req.getParameter("accountno");
		long credit=Long.parseLong(req.getParameter("amount"));
		
		SimpleDateFormat dtf=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date now=new Date();
		String date=dtf.format(now);
		HttpSession session=req.getSession();
		String name=(String)session.getAttribute("name");
		
		
		 java.sql.Connection c;
	
		try{
			Class.forName("com.mysql.cj.jdbc.Driver"); 
            c =   DriverManager.getConnection("jdbc:mysql://localhost:3306/bankdata","root","root");
                        Statement s1 = c.createStatement();
		    ResultSet rs=s1.executeQuery("select balance from accountdata where accountno='"+accountno+"'");
			while(rs.next()){
				
			      long amount=Long.parseLong(rs.getString(1));
			
			      long newbalance=amount+credit;
			      Statement s=c.createStatement();
			      s.executeUpdate("update  accountdata set balance="+newbalance+" where accountno='"+accountno+"'");
				  out.println("new balance is" +newbalance);
			}
			
			PreparedStatement ps=c.prepareStatement("insert into credit values(?,?,?,?)");
			ps.setString(1,name);
			ps.setString(2,accountno);
			ps.setLong(3,credit);
			ps.setString(4,date);
			
			ps.executeUpdate();
			
			RequestDispatcher disp=req.getRequestDispatcher("AfterLogin.html");
			disp.include(req,res);
			out.println("<htm><body align=center>money Added success </body></html>");
			
			
			
			
			
		}
		catch(Exception e){System.out.println(e);}
	  }

     }
