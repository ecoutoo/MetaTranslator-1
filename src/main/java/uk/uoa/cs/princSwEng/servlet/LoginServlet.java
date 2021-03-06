package uk.uoa.cs.princSwEng.servlet;


// import net.sf.corn.converter.ParsingException;
// import net.sf.corn.converter.json.JsTypeComplex;
// import net.sf.corn.converter.json.JsTypeList;
// import net.sf.corn.converter.json.JsonStringParser;
// import net.sf.corn.httpclient.HttpClient;
// import net.sf.corn.httpclient.HttpResponse;

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

import java.util.List;
import java.util.ArrayList;

import uk.uoa.cs.princSwEng.resource.Researcher;
import uk.uoa.cs.princSwEng.database.CreateResearcherDatabase;
import uk.uoa.cs.princSwEng.database.SearchResearcherDatabase;
import uk.uoa.cs.princSwEng.database.SearchResearcherSurveysDatabase;

public final class LoginServlet extends AbstractDatabaseServlet {
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		// forwards the control to the ManagerPage
		HttpSession session = req.getSession(true);
		if (session.getAttribute("current_logged_in") != null) {
			req.getRequestDispatcher("/jsp/manager.jsp").forward(req, res);
			//prova = (int) session.getAttribute("current_logged_in");
			//req.setAttribute("rkey",prova);
		} else {
			req.getRequestDispatcher("/jsp/login.jsp").forward(req, res);
		}
	}
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// request parameter
		int rkey = -1;
		HttpSession session = req.getSession(true);
		if (session.getAttribute("current_logged_in") != null) {
			req.getRequestDispatcher("/jsp/manager.jsp").forward(req, res);
		}
		System.out.println("Request getSession");
		System.out.println(req.getSession());
		String pwda;
		// model
		Message m = null;
		String username = null;
		String name = null;
		String surname = null;
		String email = null;
		int[] survarr = null;
		try {
			rkey = (int) Integer.parseInt((req.getParameter("rkey")));
		}
		catch (Exception e) {
			req.setAttribute("error", "Please enter a number in correct format");
			req.getRequestDispatcher("/jsp/login.jsp").forward(req, res);
		}
		pwda = req.getParameter("password");
		if (Global.DEBUGMODE)
			System.out.println("Parameters retrieved: " + rkey + pwda);
		try {
			Researcher rsc = new SearchResearcherDatabase(getConnection(), rkey).SearchResearcher();
		}
		catch (Exception e) {
			if (e instanceof NullPointerException) {
				req.setAttribute("error", "Your researcher key does not exist, please check and try again.");
				req.getRequestDispatcher("/jsp/login.jsp").forward(req, res);
			}
		}
		try {
			Researcher rsc = new SearchResearcherDatabase(getConnection(), rkey).SearchResearcher();
			String pwdb = rsc.getResearcherPassword();
			username = rsc.getResearcherUsername();
			name = rsc.getResearcherName();
			surname = rsc.getResearcherSurname();
			email = rsc.getResearcherEmail();
			survarr = new SearchResearcherSurveysDatabase(getConnection(), rkey).searchResearcherSurveys();

			if (!pwda.equals(pwdb)) {
				System.out.println("Wrong password: " + pwda + pwdb);
				req.setAttribute("error", "Incorrect researcher ID and password, please try again");
				req.getRequestDispatcher("/jsp/login.jsp").forward(req, res);
			} else {
				req.setAttribute("error", "No error has been set");
				req.setAttribute("rkey", rkey);
				session.setAttribute("rkey", rkey);
				req.setAttribute("username", username);
				req.setAttribute("name", name);
				req.setAttribute("surname", surname);
				req.setAttribute("email", email);
				req.setAttribute("survarr", survarr);
				System.out.println(email.getClass());
				req.setAttribute("myfoo", "myfoo");
				session.setAttribute("current_logged_in", rkey);
				req.getRequestDispatcher("/jsp/display-rkey.jsp").forward(req, res);
			}
		}
		catch (Exception e) {
			if (e instanceof NullPointerException) {
				req.setAttribute("error", "Your researcher key does not exist, please check and try again.");
				req.getRequestDispatcher("/jsp/login.jsp").forward(req, res);
			}
		}
	}
}
