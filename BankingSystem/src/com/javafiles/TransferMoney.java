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

public class TransferMoney extends HttpServlet {
	
	public void service(HttpServletRequest req , HttpServletResponse res)throws IOException , ServletException
	{
		res.setContentType("text/html");
		PrintWriter out=res.getWriter();
		HttpSession session=req.getSession();
		String accountno1=req.getParameter("accountno1");
		String accountno2=req.getParameter("accountno2");
		String amount1=req.getParameter("money");
		long amount=Long.parseLong(req.getParameter("money"));
		SimpleDateFormat dtf=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date now=new Date();
		String name=(String)session.getAttribute("name");
		String date=dtf.format(now);
     
		 java.sql.Connection c;
		
		try{
			Class.forName("com.mysql.cj.jdbc.Driver"); 
            c =   DriverManager.getConnection("jdbc:mysql://localhost:3306/bankdata","root","root");
                        Statement s1 = c.createStatement();;
			//deduct from accountno1
			ResultSet rs=s1.executeQuery("select balance from accountdata where accountno='"+accountno1+"'");
			while(rs.next()){
				
			      long balance=Long.parseLong(rs.getString(1));
			
			      long newbalance=balance-amount;
				  Statement s=c.createStatement();
			      s.executeUpdate("update  accountdata set balance="+newbalance+" where accountno='"+accountno1+"'");
			}
			Statement s2=c.createStatement();
			//add  to accountno2
			ResultSet rs1=s2.executeQuery("select balance from accountdata where accountno='"+accountno2+"'");
			while(rs1.next()){
				
			      long balance1=Long.parseLong(rs1.getString(1));
			
			      long newbalance1=balance1+amount;
				  Statement s3=c.createStatement();
			      s3.executeUpdate("update  accountdata set balance="+newbalance1+" where accountno='"+accountno2+"'");
				  out.println("money transfered");
			}
			PreparedStatement ps=c.prepareStatement("insert into transaction values(?,?,?,?,?)");
			ps.setString(1,name);
			ps.setString(2,accountno1);
			ps.setString(3,accountno2);
			ps.setString(4,amount1);
			ps.setString(5,date);
			ps.executeUpdate();
			
			RequestDispatcher disp=req.getRequestDispatcher("AfterLogin.html");
			disp.include(req,res);
			out.println("<htm><body align=center>money transfered </body></html>");
		}
		catch(Exception e){ out.println(e);}
	}

}
