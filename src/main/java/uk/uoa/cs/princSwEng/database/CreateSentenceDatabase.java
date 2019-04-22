package uk.uoa.cs.princSwEng.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import uk.uoa.cs.princSwEng.resource.Sentence;

public class CreateSentenceDatabase {
	private static final String STATEMENT = "INSERT INTO SENTENCES (internal_id, sentence, user_id) VALUES (?,?,?) RETURNING *";
	private final Connection con;
	private final Sentence sent;
	private final int rkey;
	
	public CreateSentenceDatabase(final Connection con, Sentence sent, int rkey) {
		this.con = con;
		this.sent = sent;
		this.rkey = rkey;
	}
	
	public int createSentence() throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = -1;
		
		try {
			pstmt = con.prepareStatement(STATEMENT);
			pstmt.setString(1,"PERSONAL");
			pstmt.setString(2,sent.getSentenceText());
			pstmt.setInt(3,rkey);
			System.err.println("Before executing executeQuery on Sentences in CreateSentenceDatabase");
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = rs.getInt("id");
			}
		} catch(SQLException ex) {
			System.err.println("SQLException invoked by the try block in CreateSentenceDatabase");
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
