package uk.uoa.cs.princSwEng.servlet;

import uk.uoa.cs.princSwEng.resource.Global;
import uk.uoa.cs.princSwEng.resource.Message;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.net.URISyntaxException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import java.util.List;
import java.util.ArrayList;

import uk.uoa.cs.princSwEng.resource.Sentence;
import uk.uoa.cs.princSwEng.resource.Survey;
import uk.uoa.cs.princSwEng.database.CreateSentenceDatabase;
import uk.uoa.cs.princSwEng.database.CreateSurveyDatabase;

public final class PersonalServlet extends AbstractDatabaseServlet {
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession(true);
		if (session.getAttribute("current_logged_in") == null) {
			req.setAttribute("Error", "Please login");
			req.getRequestDispatcher("/jsp/login.jsp").forward(req, res);
		}
		else {
			req.getRequestDispatcher("/jsp/upload.jsp").forward(req, res);
		}
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		HttpSession session = req.getSession(true);
		if (session.getAttribute("current_logged_in") == null) {
			req.setAttribute("Error", "Please login");
			req.getRequestDispatcher("/jsp/login.jsp").forward(req, res);
		}
		int rkey = -1;
		int num = -1;
		int count = 0;
		String languages;
		String translator;
		String[] prmsents;
		int[] idsents = null;
		Sentence sent;
		int survkey = -1;
		Message m = null;
		
		try {
			rkey = (int)Integer.parseInt(req.getParameter("rkey"));
			num = (int)Integer.parseInt(req.getParameter("num"));
			translator = req.getParameter("translator");
			languages = req.getParameter("languages");
			prmsents = new String[num];
			idsents = new int[num];
			for (int a=0; a<num; a++) {
				prmsents[a] = req.getParameter("ownsent"+a);
			}
			for (int b=0; b<num; b++) {
				sent = new Sentence("PERSONAL",prmsents[b]);
				idsents[b] = new CreateSentenceDatabase(getConnection(), sent, rkey).createSentence();
				System.out.println("Sentence "+b+": "+idsents[b]);
			}
			Survey surv = new Survey("PRS",translator,languages,num,idsents,rkey);
			survkey = new CreateSurveyDatabase(getConnection(), surv).createSurvey();
		} catch (SQLException ex) {
			m = new Message("Cannot find the company: unexpected error while accessing the database.",
				                "E200", ex.getMessage());
		} catch (URISyntaxException ex) {
			m = new Message("There is a problem with the URI during the database connection phase.", "DB100", ex.getMessage());
		}
		req.setAttribute("key",survkey);
		req.setAttribute("rkey",rkey);
		req.getRequestDispatcher("/jsp/display-key.jsp").forward(req, res);
	}
}
		