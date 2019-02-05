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

import uk.uoa.cs.princSwEng.resource.Sentence;
import uk.uoa.cs.princSwEng.resource.Translate;
import uk.uoa.cs.princSwEng.resource.Survey;
import uk.uoa.cs.princSwEng.database.SearchRandomSentenceDatabase;
import uk.uoa.cs.princSwEng.database.CreateSurveyDatabase;
import uk.uoa.cs.princSwEng.database.SearchSurveyDatabase;
import uk.uoa.cs.princSwEng.database.SearchSentencesFromSurveyDatabase;
import uk.uoa.cs.princSwEng.resource.Translate;
import uk.uoa.cs.princSwEng.resource.Language;

public final class HomepageServlet extends AbstractDatabaseServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * List all category.
	 *
	 * @param req the HTTP request from the client.
	 * @param res the HTTP response from the server.
	 * @throws ServletException if any error occurs while executing the servlet.
	 * @throws IOException      if any error occurs in the client/server communication.
	 */


	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		// request parameter
		int key = -1;
		Translate.setKey("XXXX-XXXX-XXXX-XXXXX-XXXX");


		// model

		Message m = null;
		String[] texts = null;
		String[] translated_texts = null;

		try {

			key = (int) Integer.parseInt((req.getParameter("key")));


			if (Global.DEBUGMODE)
				System.out.println("Parameters retrieved: " + key);


			Survey ssd = new SearchSurveyDatabase(getConnection(), key).SearchSurvey();
			List<Sentence> sent = new SearchSentencesFromSurveyDatabase(getConnection(), ssd).SearchSentencesFromSurvey();
			texts = new String[sent.size()];
			translated_texts = new String[sent.size()];
			if (true) //TODO check translation engine
			{

				{

					switch (ssd.getSurveyLanguages()){
						case "Chinese":
							if (Global.DEBUGMODE)
								System.out.println("Chinese language not supported yet, going for italian");
							for (int i = 0; i < sent.size(); i++)
							{
								texts[i] = sent.get(i).getSentenceText();
								translated_texts[i] = Translate.execute(texts[i], Language.ENGLISH, Language.ITALIAN);
							}
							break;


						case "French":
							for (int i = 0; i < sent.size(); i++)
							{
								texts[i] = sent.get(i).getSentenceText();
								translated_texts[i] = Translate.execute(texts[i], Language.ENGLISH, Language.FRENCH);
							}
							break;

						case "Italian":
							for (int i = 0; i < sent.size(); i++)
							{
								texts[i] = sent.get(i).getSentenceText();
								translated_texts[i] = Translate.execute(texts[i], Language.ENGLISH, Language.ITALIAN);
							}
							break;

						case "German":
							for (int i = 0; i < sent.size(); i++)
							{
								texts[i] = sent.get(i).getSentenceText();
								translated_texts[i] = Translate.execute(texts[i], Language.ENGLISH, Language.GERMAN);
							}
							break;
					}


				}
			}



		}/* catch (NumberFormatException ex)
		          {
		          m = new Message("Cannot read the company. Invalid input parameters: translator must be a string.",
		          "E100", ex.getMessage());
		          }*/ catch (SQLException ex) {
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

		// 
		req.setAttribute("key", key);
		req.setAttribute("texts", texts);
		req.setAttribute("translated_texts", translated_texts);

		// req.setAttribute("message", m);

		// forwards the control to the read-company-result JSP
		req.getRequestDispatcher("jsp/display-survey.jsp").forward(req, res);

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
