package com.javafiles;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import javax.swing.*;
public class LogoutServlet extends HttpServlet
{
	public void doGet(HttpServletRequest req , HttpServletResponse res)throws IOException , ServletException
	{
		res.setContentType("text/html");
		PrintWriter out=res.getWriter();
		RequestDispatcher disp=req.getRequestDispatcher("Home.html");
		disp.include(req,res);
		HttpSession session=req.getSession(false);
		session.invalidate();
		out.println("<html><body align=center>succesfully logged out </body></html>");
	}
}