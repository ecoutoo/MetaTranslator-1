package uk.uoa.cs.princSwEng.servlet;

import uk.uoa.cs.princSwEng.resource.Global;
import uk.uoa.cs.princSwEng.resource.Message;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uk.uoa.cs.princSwEng.resource.Survey;
import uk.uoa.cs.princSwEng.resource.Result;
import uk.uoa.cs.princSwEng.database.SearchResultsDatabase;
import uk.uoa.cs.princSwEng.database.SearchSurveyDatabase;

public final class AnalyticsServlet extends AbstractDatabaseServlet {
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.getRequestDispatcher("/jsp/display-rkey.jsp").forward(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		int survkey = -1;
		List<Result> resultlist = null;
		Message m = null;
		int[] sentid = null;
		int[] corres = null;
		int[] incorres = null;
		Survey sur = null;
		int numb = -1;
		int count = 0;
		int survtaken = 0;
		
		try {
			survkey = (int)Integer.parseInt(req.getParameter("survkey"));
			System.out.println("Survey key: "+ survkey);
			resultlist = new SearchResultsDatabase(getConnection(), survkey).searchResults();
			sur = new SearchSurveyDatabase(getConnection(), survkey).SearchSurvey();
			
			numb = sur.getSurveyNum();
			System.out.println("Number of sentences: " + numb);
			sentid = new int[numb];
			corres = new int[numb];
			incorres = new int[numb];
			sentid = sur.getSurveyId();
			System.out.println("Sentence ids "+sentid[0]+ " " +sentid[1]+ " " +sentid[2]+ " " +sentid[3]+ " " +sentid[4]);
			if (resultlist.size() > 0) {
				survtaken = 1;
			}
			for (Result rsl : resultlist) {
				if (count == numb) {
					count = 0;
					survtaken = survtaken + 1;
				}
				if (rsl.getResultCorrect() == true) {
					corres[count] = corres[count] + 1;
				}
				if (rsl.getResultCorrect() == false) {
					incorres[count] = incorres[count] + 1;
				}
				count = count + 1;
			}
			System.out.println("Correct "+corres[0]+ " " +corres[1]+ " " +corres[2]+ " " +corres[3]+ " " +corres[4]);
			System.out.println("Incorrect "+incorres[0]+ " " +incorres[1]+ " " +incorres[2]+ " " +incorres[3]+ " " +incorres[4]);
		} catch (SQLException ex) {
			m = new Message("Cannot find the company: unexpected error while accessing the database.",
				                "E200", ex.getMessage());
		} catch (URISyntaxException ex) {
			m = new Message("There is a problem with the URI during the database connection phase.", "DB100", ex.getMessage());
		}
		req.setAttribute("sentid", sentid);
		req.setAttribute("corres", corres);
		req.setAttribute("incorres", incorres);
		req.setAttribute("survkey", survkey);
		req.setAttribute("survtaken", survtaken);
		req.setAttribute("numb", numb);
		req.getRequestDispatcher("/jsp/analytics.jsp").forward(req, res);
	}
}
