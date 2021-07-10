package com.javafiles;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import java.util.*;
public class TransferDetailsUser extends HttpServlet
{
	public void service(HttpServletRequest req , HttpServletResponse res)throws IOException , ServletException
	{
		res.setContentType("text/html");
		PrintWriter out=res.getWriter();
		HttpSession session=req.getSession();
		String name=(String)session.getAttribute("name");
		java.sql.Connection c;
		try{
			Class.forName("com.mysql.cj.jdbc.Driver"); 
            c =   DriverManager.getConnection("jdbc:mysql://localhost:3306/bankdata","root","root");
			PreparedStatement ps=c.prepareStatement("select * from transaction where name='"+name+"'");
	        ResultSet rs=ps.executeQuery();
			
				out.println("<html><body>");
				out.println("<table border='2'>");
				out.println("<tr><th>ser no.</th> <th>NAME</th><th>SENDER</th><th>RECIEVER</th><th>AMOUNT</th><th>DATE</th></tr>");
				int serno=1;
				while(rs.next()){
					out.println("<tr><td>"+serno+"</td><td>"+rs.getString(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td><td>"+rs.getString(4)+"</td><td>"+rs.getString(5)+"</td><tr>");
					serno++;
				}
				
			
		}
		catch(Exception e){
			System.out.println(e);
			out.println(e);
		}
	}
}
