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

import uk.uoa.cs.princSwEng.resource.Sentence;
import uk.uoa.cs.princSwEng.resource.Survey;
import uk.uoa.cs.princSwEng.database.SearchRandomSentenceDatabase;
import uk.uoa.cs.princSwEng.database.CreateSurveyDatabase;

public final class ManagerServlet extends AbstractDatabaseServlet
{
private static final long serialVersionUID = 1L;
private final static String SENDGRID_API_KEY ="XXXX-XXXX-XXXX-XXXX-XXXX";

/**
 * List all category.
 *
 * @param req
 *            the HTTP request from the client.
 * @param res
 *            the HTTP response from the server.
 *
 * @throws ServletException
 *             if any error occurs while executing the servlet.
 * @throws IOException
 *             if any error occurs in the client/server communication.
 */


public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
{

		
		// forwards the control to the ManagerPage
		req.getRequestDispatcher("/html/manager.html").forward(req, res);

}


public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
{

		// request parameter
		String translator;
		String languages;
		int number;
		String corpora;
		List<Sentence> sentences; 
		int[] arr;
		int key = -1;
		String email = "";


		// model
		
		Message m = null;

		try
		{

				translator = req.getParameter("translator");
				languages = req.getParameter("languages");
				number = (int)Integer.parseInt((req.getParameter("number")));
				corpora = req.getParameter("corpora");
				email = req.getParameter("email");

				arr = new int[number];

				if (Global.DEBUGMODE)
					System.out.println("Parameters retrieved: " + translator + languages + number + corpora);

				switch (corpora)
				{
					case "VUA":
						sentences = new SearchRandomSentenceDatabase(getConnection(), "VUA%", number).searchRandomSentence();
						break;
					case "MOH":
						sentences = new SearchRandomSentenceDatabase(getConnection(), "VUA%", number).searchRandomSentence();
						break;
					case "FLA":
						sentences = new SearchRandomSentenceDatabase(getConnection(), "VUA%", number).searchRandomSentence();
						break;
					default: 
						System.err.println("No value received for the corpora, terminating");
						return;

				}
				for (int i=0; i<sentences.size(); i++)
					arr[i] = (int)sentences.get(i).getSentenceId();

				Survey sur = new Survey(corpora, translator, languages, number, arr);


				key = new CreateSurveyDatabase(getConnection(), sur).createSurvey();

		}/* catch (NumberFormatException ex)
		          {
		          m = new Message("Cannot read the company. Invalid input parameters: translator must be a string.",
		          "E100", ex.getMessage());
		          }*/
		catch (SQLException ex)
		{
				m = new Message("Cannot find the company: unexpected error while accessing the database.",
				                "E200", ex.getMessage());
		}
		catch (URISyntaxException ex)
		{
			m = new Message("There is a problem with the URI during the database connection phase.", "DB100", ex.getMessage());
		}


		if (!email.equals("")) 
		{
			for (String retval: email.split(";")) 
			{
		        Email from = new Email("do-not-reply@metatranslate.com");  
			    String subject = "A survey was generated for you...";
			    Email to = new Email(retval);
			    Content content = new Content("text/plain", "Testing out the integration to send automatically emails when you generate a survey. Type on Metatranslate website the key to access the survey. Unique key to access your survey is: " + String.valueOf(key));  
			    Mail mail = new Mail(from, subject, to, content);

			    //SendGrid sg = new SendGrid(System.getenv("SENDGRID_API_KEY"));
			    SendGrid sg = new SendGrid(SENDGRID_API_KEY);

			    //String API_KEY = System.getenv("SENDGRIP_API_KEY");
			    //System.err.println("SENDGRIP_API_KEY: " + API_KEY);
			    Request request = new Request();
			    try {
			      request.setMethod(Method.POST);
			      request.setEndpoint("mail/send");
			      request.setBody(mail.build());
			      Response response = sg.api(request);
			      if (Global.DEBUGMODE)
			      	{
			      		System.out.println(response.getStatusCode());
			      		System.out.println(response.getBody());
			      		System.out.println(response.getHeaders());
			      	}
			    } catch (IOException ex) {
			      m = new Message("Error with SendGrip", "EM400", ex.getMessage());
			      res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				  m.toJSON(res.getOutputStream());
			    }

	     	}			
		}
	

		





		// stores the deleted company and the message as a request attribute
		 req.setAttribute("key", key);
		// req.setAttribute("message", m);

		// forwards the control to the read-company-result JSP
		req.getRequestDispatcher("/jsp/display-key.jsp").forward(req, res);

}



}
