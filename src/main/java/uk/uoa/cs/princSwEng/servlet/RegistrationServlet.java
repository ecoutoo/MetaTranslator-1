package uk.uoa.cs.princSwEng.servlet;

import com.sendgrid.*;
import java.io.IOException;

import uk.uoa.cs.princSwEng.resource.Global;
import uk.uoa.cs.princSwEng.resource.Mailer;
//import uk.uoa.cs.princSwEng.resource.SendEmail;
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

import java.util.List;
import java.util.ArrayList;

import uk.uoa.cs.princSwEng.resource.Researcher;
import uk.uoa.cs.princSwEng.database.CreateResearcherDatabase;

public final class RegistrationServlet extends AbstractDatabaseServlet
{
	private static final long serialVersionUID = 1L;
    private String to;
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		// forwards the control to the ManagerPage
		req.getRequestDispatcher("/jsp/registration.jsp").forward(req, res);
	}

    String hostname = "smtp.gmail.com";
    int port = 587;
    String username = "MetaTranslate19@gmail.com";
    String password = "EchoTeam19*";
    private Mailer mailer = new Mailer(hostname, port, username, password);
    
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		// request parameter
		int rkey = -1;
		// model
		Message m = null;
        String message = null;
        String subject = null;
		String username = null;
		String name = null;
		String surname = null;
		String email = null;
		String password = null;
		int[] survarr = new int[0];
		
		try
		{
			System.out.println("Try");
			username = req.getParameter("username");
			name = req.getParameter("name");
			surname = req.getParameter("surname");
			email = req.getParameter("email");
			password = req.getParameter("password");

			if (username == ""|| name == ""|| surname == ""|| email == ""|| password == "") {
				System.out.println("some parameters are empty");
				req.setAttribute("error", "Please ensure all fields are filled out");
				req.getRequestDispatcher("/jsp/registration.jsp").forward(req, res);
			}
			//cpassword = req.getParameter("cpassword");
			if (Global.DEBUGMODE)
				System.out.println("Parameters retrieved: " + username + name + surname + email + password);
			Researcher resea = new Researcher(username, name, surname, email, password);
			rkey = new CreateResearcherDatabase(getConnection(), resea).createResearcher();
            
            subject = "MetaTranslate Credentials";            
            message = "Welcome to MetaTranslate, your username is: " + username + ", your password is: " + password + " and your researcher key is: " + rkey;
            mailer.send(email, subject, message);

		}
		catch (SQLException ex)
		{
            m = new Message("Cannot find the company: unexpected error while accessing the database.",
				                "E200", ex.getMessage());
		}
		catch (URISyntaxException ex)
		{
			m = new Message("There is a problem with the URI during the database connection phase.", "DB100", ex.getMessage());
		}
		req.setAttribute("rkey",rkey);
		req.setAttribute("username",username);
		req.setAttribute("name",name);
		req.setAttribute("surname",surname);
		req.setAttribute("email",email);
		req.setAttribute("survarr",survarr);
		HttpSession session = req.getSession(true);
		session.setAttribute("current_logged_in",rkey);
		req.getRequestDispatcher("/jsp/display-rkey.jsp").forward(req,res);
	}
}
