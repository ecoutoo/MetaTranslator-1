package uk.uoa.cs.princSwEng.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import uk.uoa.cs.princSwEng.resource.Result;

public class CreateResultDatabase {
	private static final String STATEMENT = "INSERT INTO SURVEYRESULTS (sentence, group_id, correct, problem_phrase, confidence, own_translation) VALUES (?,?,?,?,?,?) RETURNING *";
	private final Connection con;
	private final Result survres;
	
	public CreateResultDatabase(final Connection con, Result survres) {
		this.con = con;
		this.survres = survres;
	}
	
	public int createSurveyResult() throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int reskey = -1;
		try {
			pstmt = con.prepareStatement(STATEMENT);
			pstmt.setInt(1,survres.getResultSentence());
			pstmt.setInt(2,survres.getResultGroup());
			pstmt.setBoolean(3,survres.getResultCorrect());
			pstmt.setString(4,survres.getResultProblemPhrase());
			pstmt.setInt(5,survres.getResultConfidence());
			pstmt.setString(6,survres.getResultOwnTranslation());
			System.err.println("Before executing executeQuery on CreateResultDatabase");
			rs = pstmt.executeQuery();
			if (rs.next()) {
				reskey = rs.getInt("id");
			}
		}
		catch(SQLException ex) {
			System.err.println("SQLException invoked by the try block in CreateResultDatabase");
			System.err.println(ex.getMessage());
		}
		finally {
			if (pstmt != null) {
				pstmt.close();
			}
			con.close();
		}
		return reskey;
	}
}
