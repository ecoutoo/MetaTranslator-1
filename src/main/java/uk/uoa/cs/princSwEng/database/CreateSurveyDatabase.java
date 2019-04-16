package uk.uoa.cs.princSwEng.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import uk.uoa.cs.princSwEng.resource.Survey;

public class CreateSurveyDatabase
{
	private static final String STATEMENT = "INSERT INTO USERSURVEYRECORD (user_id, survey_id) VALUES (?,?) RETURNING *";
	private static final String STATEMENT5 = "INSERT INTO SURVEYS (id, corpora, translator, languages, num, sent1, sent2, sent3, sent4, sent5) VALUES (?,?,?,?,?,?,?,?,?,?) RETURNING *";
	private static final String STATEMENT10 = "INSERT INTO SURVEYS (id, corpora, translator, languages, num, sent1, sent2, sent3, sent4, sent5, sent6, sent7, sent8, sent9, sent10) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) RETURNING *";
	private static final String STATEMENT15 = "INSERT INTO SURVEYS (id, corpora, translator, languages, num, sent1, sent2, sent3, sent4, sent5, sent6, sent7, sent8, sent9, sent10, sent11, sent12, sent13, sent14, sent15) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) RETURNING *";
	private final Connection con;
	private final Survey surv;
	private final String AlphaNumeric = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";

	public CreateSurveyDatabase(final Connection con, Survey survey)
	{
		this.con = con;
		this.surv = survey;
	}

	public String randomString() {
		System.out.println("randomString method called");
	    char myKey[] = new char[19];
	    for (int i = 0; i < 19; i++) {
            myKey[i] = AlphaNumeric.charAt((int)(Math.random() * 100000) % AlphaNumeric.length());
			//myKey[i] = AlphaNumeric.charAt(i);
            System.out.println(myKey[i]);
        }
	    String charString = new String(myKey);
	    System.out.println("Generated key: " + charString);
	    return charString;
    }
/**
 * Stores a new Category into the database
 *
 * @throws SQLException
 *             if any error occurs while storing the Category.
 */
	public String createSurvey() throws SQLException
	{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String result = "-1";
		int numbersofSentences = surv.getSurveyNum();
		String sentences = STATEMENT15; //default
		try 
		{
			switch(numbersofSentences) {
			case 5:
				sentences = STATEMENT5;
				break;
			case 10:
				sentences = STATEMENT10;
				break;
			case 15:
				sentences = STATEMENT15;
				break;
		}
					pstmt = con.prepareStatement(sentences);
                    pstmt.setString(1, randomString());
					pstmt.setString(2, surv.getSurveyCorpora());
					pstmt.setString(3, surv.getSurveyTranslator());
					pstmt.setString(4, surv.getSurveyLanguages());
					pstmt.setInt(5, surv.getSurveyNum());
					for (int i=0; i<surv.getSurveyNum(); i++)
						pstmt.setInt(i+6, surv.getSurveyId()[i]);
			rs = pstmt.executeQuery();

			if (rs.next())
			{
				result = rs.getString("id");
			}

		}
			//System.err.println("Before executing executeQuery on Surveys in CreateSurveyDatabase");
			//rs = pstmt.executeQuery();




			/*
			pstmt = con.prepareStatement(STATEMENT);
			pstmt.setString(1,surv.getSurveyRkey());
			//pstmt.setString(2,result);
            pstmt.setString(2,"hjasheahsease");
			System.err.println("Before executing executeQuery on UserSurveyRecord in CreateSurveyDatabase");
			rs = pstmt.executeQuery();

			 */

		catch(SQLException ex)
		{
			System.err.println("SQLException invoked by the try block in CreateSurveyDatabase");
			System.err.println(ex.getMessage());
		}
		finally
		{
			if (pstmt != null)
			{
				pstmt.close();
			}
			con.close();
		}
		return result;
	}
}
