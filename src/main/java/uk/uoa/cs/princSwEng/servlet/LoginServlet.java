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
import javax.sql.DataSource;

import java.util.List;
import java.util.ArrayList;

import uk.uoa.cs.princSwEng.resource.Researcher;
import uk.uoa.cs.princSwEng.database.CreateResearcherDatabase;
import uk.uoa.cs.princSwEng.database.SearchResearcherDatabase;

public final class LoginServlet extends AbstractDatabaseServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * List all category.
	 *
	 * @param req the HTTP request from the client.
	 * @param res the HTTP response from the server.
	 * @throws ServletException if any error occurs while executing the servlet.
	 * @throws IOException      if any error occurs in the client/server communication.
	 */
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		// forwards the control to the ManagerPage
		req.getRequestDispatcher("/html/login.html").forward(req, res);
	}
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// request parameter
		int rkey = -1;
		String pwda;
		// model
		Message m = null;
		try {
			rkey = (int) Integer.parseInt((req.getParameter("rkey")));
			pwda = req.getParameter("password");
			if (Global.DEBUGMODE)
				System.out.println("Parameters retrieved: " + rkey + pwda);
			Researcher rsc = new SearchResearcherDatabase(getConnection(), rkey).SearchResearcher();
			String pwdb = rsc.getResearcherPassword();
			String username = rsc.getResearcherUsername();
			String name = rsc.getResearcherName();
			String surname = rsc.getResearcherSurname();
			String email = rsc.getResearcherEmail();
			if (pwda != pwdb) {
				System.out.println("Wrong password: " + pwda + pwdb);
			}
		}/* catch (NumberFormatException ex)
		          {
		          m = new Message("Cannot read the company. Invalid input parameters: translator must be a string.",
		          "E100", ex.getMessage());
		          }*/ 
		catch (SQLException ex) {
			m = new Message("Cannot find the company: unexpected error while accessing the database.",
					"E200", ex.getMessage());
		} catch (URISyntaxException ex) {
			m = new Message("There is a problem with the URI during the database connection phase.", "DB100", ex.getMessage());
		}
		// catch (ParsingException e) {
		// 	e.printStackTrace();
		// } 
		catch(Exception e)
		{
			e.printStackTrace();
		}		
		req.setAttribute("rkey", rkey);
//		req.setAttribute("username", username);
//		req.setAttribute("name", name);
//		req.setAttribute("surname", surname);
//		req.setAttribute("email", email);
		// req.setAttribute("message", m);
		// forwards the control to the read-company-result JSP
		req.getRequestDispatcher("jsp/display-rkey.jsp").forward(req, res);

	}


	// public String translate(String key, String text, String language, String format) throws ParsingException, IOException, URISyntaxException {
 //        String url = "https://translate.yandex.net/api/v1.5/tr.json/translate?key=" + key + "&text=" + URLEncoder.encode(text, "UTF-8") + "&lang" + language;
        
	// 	HttpClient client = new HttpClient(new URI(url)); //+ "&format" + format
	// 	HttpResponse response = client.sendData(HttpClient.HTTP_METHOD.GET);
	// 	if (!response.hasError()) {
	// 		String jsonString = response.getData();
	// 		// if(Global.DEBUGMODE)
	// 		// 	System.out.println("The response is: " + jsonString);
	// 		JsTypeComplex jsonResponse = (JsTypeComplex) JsonStringParser.parseJsonString(jsonString);
	// 		JsTypeList resultsArr = (JsTypeList) jsonResponse.get("results");
	// 		return jsonString;


	// 	}
		
	// 	return "Error";
	// }

}
