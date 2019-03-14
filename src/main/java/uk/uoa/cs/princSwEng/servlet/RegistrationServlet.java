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

import uk.uoa.cs.princSwEng.resource.Researcher;
import uk.uoa.cs.princSwEng.database.CreateResearcherDatabase;

public final class RegistrationServlet extends AbstractDatabaseServlet
{
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		// forwards the control to the ManagerPage
		req.getRequestDispatcher("/html/registration.html").forward(req, res);
	}
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		// request parameter
		int rkey = -1;
		// model
		Message m = null;
		try
		{
			String username = req.getParameter("username");
			String name = req.getParameter("name");
			String surname = req.getParameter("surname");
			String email = req.getParameter("email");
			String password = req.getParameter("password");
			//cpassword = req.getParameter("cpassword");
			if (Global.DEBUGMODE)
				System.out.println("Parameters retrieved: " + username + name + surname + email + password);
			Researcher resea = new Researcher(username, name, surname, email, password);
			rkey = new CreateResearcherDatabase(getConnection(), resea).createResearcher();

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
	}
}
