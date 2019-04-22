package uk.uoa.cs.princSwEng.servlet;

import uk.uoa.cs.princSwEng.resource.Message;

import java.io.OutputStream;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import uk.uoa.cs.princSwEng.resource.Survey;
import uk.uoa.cs.princSwEng.resource.Result;
import uk.uoa.cs.princSwEng.database.SearchResultsDatabase;

import java.util.concurrent.TimeUnit;

import java.io.FileWriter;


public final class DownloadRawServlet extends AbstractDatabaseServlet {
    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/display-rkey.jsp").forward(req, res);
        System.out.println("Get method of DownloadRawServlet.java");
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        System.out.println("Post method of DownloadRawServlet.java");

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
            //System.out.println("TRY TRY TRY");
            //new SearchRandomSentenceDatabase(getConnection(), "VUA%", number).searchRandomSentence();
            List<Result> mySurvey = new SearchResultsDatabase(getConnection(),16547).searchResults();
            System.out.println("RESULTS RECEIVED!");
            try {
                res.setContentType("text/csv");
                res.setHeader("Content-Disposition", "attachment; filename=\"raw_results.csv\"");
                try {
                    OutputStream outputStream = res.getOutputStream();
                    outputStream.write("sentence, group_id, correct, problem_phrase, confidence, own_translation".getBytes());
                    for (int i = 0; i < mySurvey.size(); i++)
                    {
                        System.out.println(i);
                        outputStream.write("\n".getBytes());
                        String sentence = Integer.toString(mySurvey.get(i).getResultSentence());
                        String rg = Integer.toString(mySurvey.get(i).getResultGroup());
                        String cor = Boolean.toString(mySurvey.get(i).getResultCorrect());
                        String problem = mySurvey.get(i).getResultProblemPhrase();
                        String confidence = Integer.toString(mySurvey.get(i).getResultConfidence());
                        String ownSentence = mySurvey.get(i).getResultOwnTranslation();
                        if (ownSentence != null || ownSentence == ""){
                            ownSentence = "USER HAS NOT SUBMITTED A SENTENCE";
                        }
                        outputStream.write((sentence + ',' + rg + ',' + cor + ',' + problem + ',' + confidence + ',' + ownSentence).getBytes());
                        //outputStream.write(mySurvey.get(i).getResultSentence() + ',' + Integer.toString(mySurvey.get(i).getResultGroup()) + ',' + mySurvey.get(i).getResultCorrect() + ',' + mySurvey.get(i).getResultProblemPhrase() + ',' + mySurvey.get(i).getResultConfidence() + ',' + mySurvey.get(i).getResultOwnTranslation());
                        System.out.println(mySurvey.get(i).getResultOwnTranslation());
                    }

                    //outputStream.write(outputResult.getBytes());
                    outputStream.flush();
                    outputStream.close();
                    System.out.println("Outputstream closed");
                } catch (Exception e) {
                    System.out.println(e.toString());
                }
                //System.out.println(theString);


                //writers.println("HASEHASHE");
                //writers.close();
                //writer.flush();
                //writer.close();

            }

            catch (Exception e) {
                System.out.println(e);
                System.out.println("Writer not initialised");
            }

            //System.out.println(mySurvey);
            //System.out.println("mySurvey");

            try {
                TimeUnit.MILLISECONDS.sleep(2000);
            }
            catch (InterruptedException e) {
                System.err.println("InterruptedException has occured");
                System.err.println(e);
            }

            try {
                if (true) {
                    System.out.println("Sending user to file");
                    res.sendRedirect("/Echo_Team_war/metatranslaterawdata.csv");
                    return;
                }

            }
            catch (Exception e) {
                System.err.println(e);
            }

			/*
			System.out.println(".............................................");
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
			if (survtaken > 0) {
				survtaken = survtaken + 1;
			}
			System.out.println("Correct "+corres[0]+ " " +corres[1]+ " " +corres[2]+ " " +corres[3]+ " " +corres[4]);
			System.out.println("Incorrect "+incorres[0]+ " " +incorres[1]+ " " +incorres[2]+ " " +incorres[3]+ " " +incorres[4]);
			*/
        }
        catch (Exception e) {
            System.err.println(e);
        }
		/*
		catch (SQLException ex) {
			m = new Message("Cannot find the company: unexpected error while accessing the database.",
				                "E200", ex.getMessage());
		} catch (URISyntaxException ex) {
			m = new Message("There is a problem with the URI during the database connection phase.", "DB100", ex.getMessage());
		}

		 */
        req.setAttribute("sentid", sentid);
        req.setAttribute("corres", corres);
        req.setAttribute("incorres", incorres);
        req.setAttribute("survkey", survkey);
        req.setAttribute("survtaken", survtaken);
        req.setAttribute("numb", numb);
        req.getRequestDispatcher("/jsp/downloadraw.jsp").forward(req, res);
    }
}
