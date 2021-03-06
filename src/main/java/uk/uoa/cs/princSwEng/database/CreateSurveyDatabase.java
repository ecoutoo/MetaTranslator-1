package uk.uoa.cs.princSwEng.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import uk.uoa.cs.princSwEng.resource.Survey;

public class CreateSurveyDatabase {
	//private static final String STATEMENT = "INSERT INTO USERSURVEYRECORD (user_id, survey_id) VALUES (?,?) RETURNING *";
	private static final String STATEMENT5 = "INSERT INTO SURVEYS (user_id, corpora, translator, languages, num, sent1, sent2, sent3, sent4, sent5) VALUES (?,?,?,?,?,?,?,?,?,?) RETURNING *";
	private static final String STATEMENT10 = "INSERT INTO SURVEYS (user_id, corpora, translator, languages, num, sent1, sent2, sent3, sent4, sent5, sent6, sent7, sent8, sent9, sent10) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) RETURNING *";
	private static final String STATEMENT15 = "INSERT INTO SURVEYS (user_id, corpora, translator, languages, num, sent1, sent2, sent3, sent4, sent5, sent6, sent7, sent8, sent9, sent10, sent11, sent12, sent13, sent14, sent15) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) RETURNING *";
	private final Connection con;
	private final Survey surv;

	public CreateSurveyDatabase(final Connection con, Survey survey) {
		this.con = con;
		this.surv = survey;
	}

/**
 * Stores a new Category into the database
 *
 * @throws SQLException
 *             if any error occurs while storing the Category.
 */
	public int createSurvey() throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = -1;
		int sentnum = surv.getSurveyNum();

		try {
			switch (sentnum) {
				case 5:
					pstmt = con.prepareStatement(STATEMENT5);
					break;
				case 10:
					pstmt = con.prepareStatement(STATEMENT10);
					break;
				case 15:
					pstmt = con.prepareStatement(STATEMENT15);
					break;
				default: 
					System.err.println("Error, switch case default value in CreateSurveyDatabase");
			}
			pstmt.setInt(1, surv.getSurveyRkey());
			pstmt.setString(2, surv.getSurveyCorpora());
			pstmt.setString(3, surv.getSurveyTranslator());
			pstmt.setString(4, surv.getSurveyLanguages());
			pstmt.setInt(5, surv.getSurveyNum());
			for (int i=0; i<sentnum; i++)
				pstmt.setInt(i+6, surv.getSurveyId()[i]);
			System.err.println("Before executing executeQuery on Surveys in CreateSurveyDatabase");
			
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = rs.getInt("id");
			}
		}
		catch(SQLException ex) {
			System.err.println("SQLException invoked by the try block in CreateSurveyDatabase");
			System.err.println(ex.getMessage());
		}
		finally {
			if (pstmt != null) {
				pstmt.close();
			}
			con.close();
		}
		return result;
	}
}
