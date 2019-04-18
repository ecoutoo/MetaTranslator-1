package uk.uoa.cs.princSwEng.servlet;

import com.sendgrid.*;
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
import javax.sql.DataSource;

import java.util.List;
import java.util.ArrayList;

import uk.uoa.cs.princSwEng.resource.Result;
import uk.uoa.cs.princSwEng.resource.Survey;
import uk.uoa.cs.princSwEng.database.CreateResultDatabase;
import uk.uoa.cs.princSwEng.database.SearchSurveyDatabase;

public final class CompletionServlet extends AbstractDatabaseServlet {
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.getRequestDispatcher("/jsp/display-survey.jsp").forward(req,res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
	{
		//request parameter
		int survsize = -1;
		int group = -1;
		String[] prmcorrect = null;
		String[] problemphrase = null;
		String[] prmconfidence = null;
		String[] owntranslation = null;
		String prblph = "";
		//model
		Message m = null;
		int reskey = -1;
		int lo1 = 0;
		int lo2 = 0;
		int lo3 = 0;
		int lo4 = 0;
		boolean[] correct = null;
		int[] sentence = null;
		int[] confidence = null;
		String prova = null;
		
		try {
			survsize = (int)Integer.parseInt((req.getParameter("survsize")));
			group = (int)Integer.parseInt(req.getParameter("survkey"));
			Survey surv = new SearchSurveyDatabase(getConnection(), group).SearchSurvey();
			sentence = new int[survsize];
			sentence = surv.getSurveyId();
			prmcorrect = new String[survsize];
			while (lo3 < survsize) {
				prmcorrect[lo3] = req.getParameter("CorrectTranslation"+lo3);
				lo3 += 1;
			}
			problemphrase = new String[survsize];
			while (lo4 < survsize) {
				prblph = "";
				String prmproblemphrase[] = req.getParameterValues("IncorrectWord"+lo4);
				for (int prv = 0; prv < prmproblemphrase.length; prv++) {
					prblph = prblph + " " + prmproblemphrase[prv];
				}
				problemphrase[lo4] = prblph;
				lo4 += 1;
			}
			prmconfidence = new String[survsize];
			prmconfidence = req.getParameterValues("Confidence");
			owntranslation = new String[survsize];
			owntranslation = req.getParameterValues("OwnTranslation");
			if (Global.DEBUGMODE) {
				System.out.println("Parameters retrieved: " + sentence + group + survsize);
			}
			correct = new boolean[survsize];
			confidence = new int[survsize];
			while (lo1 < survsize) {
				if (prmcorrect[lo1].equals("Correct")) {
					correct[lo1] = true;
					confidence[lo1] = 0;
					problemphrase[lo1] = "";
				} else if (prmcorrect[lo1].equals("Incorrect")) {
					correct[lo1] = false;
					confidence[lo1] = (int)Integer.valueOf(prmconfidence[lo1]);
				}
				lo1 += 1;
			}
			while (lo2 < survsize) {
				Result loadres = new Result(sentence[lo2],group,correct[lo2],problemphrase[lo2],confidence[lo2],owntranslation[lo2]);
				reskey = new CreateResultDatabase (getConnection(), loadres).createSurveyResult();
				lo2 += 1;
			}
		}
		catch (SQLException ex) {
			m = new Message("Cannot find the company: unexpected error while accessing the database.","E200", ex.getMessage());
		}
		catch (URISyntaxException ex)
		{
			m = new Message("There is a problem with the URI during the database connection phase.", "DB100", ex.getMessage());
		}
		req.setAttribute("survkey",group);
		req.setAttribute("survsize",survsize);
		req.getRequestDispatcher("/jsp/completion.jsp").forward(req,res);
	}
}
		