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
		//model
		Message m = null;
		int reskey = -1;
		int lo11 = 0;
		int lo12 = lo11;
		int currres2 = 0;
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
//			prmsentence = new String[survsize];
//			prmsentence = req.getParameterValues("sentids");
			prmcorrect = new String[survsize];
			prmcorrect = req.getParameterValues("CorrectTranslation");
			problemphrase = new String[survsize];
			problemphrase = req.getParameterValues("IncorrectWord");
			prmconfidence = new String[survsize];
			prmconfidence = req.getParameterValues("Confidence");
			owntranslation = new String[survsize];
			owntranslation = req.getParameterValues("OwnTranslation");
			if (Global.DEBUGMODE) {
				System.out.println("Parameters retrieved: " + sentence + group + survsize);
			}
			correct = new boolean[survsize];
			confidence = new int[survsize];
			while (lo11 < survsize) {
				lo12 = lo11;
				prova = prmcorrect[lo12];
				if (prova.equals("Correct")) {
					correct[lo12] = true;
				} else if (prova.equals("Incorrect")) {
					correct[lo12] = false;
				}
				confidence[lo12] = (int)Integer.valueOf(prmconfidence[lo12]);
				lo11 = lo11 + 1;
			}
			while (currres2 < survsize) {
				Result loadres = new Result(sentence[currres2],group,correct[currres2],problemphrase[currres2],confidence[currres2],owntranslation[currres2]);
				reskey = new CreateResultDatabase (getConnection(), loadres).createSurveyResult();
				currres2 = currres2 + 1;
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
		