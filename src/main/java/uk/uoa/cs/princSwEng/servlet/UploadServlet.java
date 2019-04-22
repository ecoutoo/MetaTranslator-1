package uk.uoa.cs.princSwEng.servlet;

//import com.sendgrid.*;
import java.io.IOException;

import uk.uoa.cs.princSwEng.resource.Global;
import uk.uoa.cs.princSwEng.resource.Message;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.net.URISyntaxException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

public final class UploadServlet extends AbstractDatabaseServlet {
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession(true);
		if (session.getAttribute("current_logged_in") == null) {
			req.setAttribute("Error", "Please login");
			req.getRequestDispatcher("/jsp/login.jsp").forward(req, res);
		}
		else {
			req.getRequestDispatcher("/jsp/display-rkey.jsp").forward(req, res);
		}
	}
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession(true);
		if (session.getAttribute("current_logged_in") == null) {
			req.setAttribute("Error", "Please login");
			req.getRequestDispatcher("/jsp/login.jsp").forward(req, res);
		}
		//parameters
		int rkey;
		int num;
		//model
		Message m = null;
		rkey = (int)Integer.parseInt(req.getParameter("rkey"));
		num = (int)Integer.parseInt(req.getParameter("num"));
		if (Global.DEBUGMODE) 
			System.out.println("Parameter retrieved: " + rkey + num);
		req.setAttribute("rkey",rkey);
		req.setAttribute("num",num);
		session.setAttribute("current_logged_in", "rkey");
		req.getRequestDispatcher("/jsp/upload.jsp").forward(req, res);
	}
}
