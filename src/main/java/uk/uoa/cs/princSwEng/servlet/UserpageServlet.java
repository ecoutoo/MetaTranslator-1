package uk.uoa.cs.princSwEng.servlet;

import uk.uoa.cs.princSwEng.resource.Message;
import uk.uoa.cs.princSwEng.resource.Global;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URLEncoder;
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

import uk.uoa.cs.princSwEng.resource.Researcher;
import uk.uoa.cs.princSwEng.database.SearchResearcherDatabase;
import uk.uoa.cs.princSwEng.database.SearchResearcherSurveysDatabase;

public final class UserpageServlet extends AbstractDatabaseServlet {
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession(true);
		if (session.getAttribute("current_logged_in") == null) {
			req.setAttribute("Error", "Please login");
			req.getRequestDispatcher("/jsp/login.jsp").forward(req, res);
		}
		else {
			int rkey = -1;
			String username = null;
			String name = null;
			String surname = null;
			String email = null;
			int[] survarr = null;
		//model
			Message m = null;
			try {
				rkey =  (int) session.getAttribute("rkey");
				if (Global.DEBUGMODE) 
					System.out.println("Userpage Parameter retrieved: " + rkey);
				Researcher rsc = new SearchResearcherDatabase(getConnection(), rkey).SearchResearcher();
				username = rsc.getResearcherUsername();
				name = rsc.getResearcherName();
				surname = rsc.getResearcherSurname();
				email = rsc.getResearcherEmail();
				survarr = new SearchResearcherSurveysDatabase(getConnection(), rkey).searchResearcherSurveys();
			} catch (Exception e) {
				if (e instanceof NullPointerException) {
					req.setAttribute("error", "Your researcher key does not exist, please check and try again.");
					req.getRequestDispatcher("/jsp/login.jsp").forward(req, res);
				}
			}
			req.setAttribute("rkey", rkey);
			session.setAttribute("rkey", rkey);
			req.setAttribute("username", username);
			req.setAttribute("name", name);
			req.setAttribute("surname", surname);
			req.setAttribute("email", email);
			req.setAttribute("survarr", survarr);
			session.setAttribute("current_logged_in", rkey);
			req.getRequestDispatcher("/jsp/display-rkey.jsp").forward(req, res);
		}
	}
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession(true);
		if (session.getAttribute("current_logged_in") == null) {
			req.setAttribute("Error", "Please login");
			req.getRequestDispatcher("/jsp/login.jsp").forward(req, res);
		}
	}
}
