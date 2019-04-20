package uk.uoa.cs.princSwEng.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import uk.uoa.cs.princSwEng.resource.Global;
import uk.uoa.cs.princSwEng.resource.Result;

public class SearchResultsDatabase {
	private static final String STATEMENT = "SELECT * FROM SURVEYRESULTS WHERE group_id=?";
	private final Connection con;
	private final int survkey;
	
	public SearchResultsDatabase(final Connection con, int survkey) {
		this.con = con;
		this.survkey = survkey;
	}
	
	public List<Result> searchResults() throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Result> resultlist = new ArrayList<Result>();
		
		try {
			pstmt = con.prepareStatement(STATEMENT);
			pstmt.setInt(1,survkey);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int sentence = rs.getInt("sentence");
				int group_id = rs.getInt("group_id");
				boolean correct = rs.getBoolean("correct");
				String problem_phrase = rs.getString("problem_phrase");
				int confidence = rs.getInt("confidence");
				String own_translation = rs.getString("own_translation");
				if (Global.DEBUGMODE) {
					System.out.println("SearchResultsDatabase> Parameters retrieved: " + sentence + group_id + correct + problem_phrase + confidence + own_translation);
				}
				resultlist.add(new Result(sentence,group_id,correct,problem_phrase,confidence,own_translation));
			}
		} catch(SQLException ex) {
			System.err.println("SQLException invoked by try block in SearchResultsDatabase");
			System.err.println(ex.getMessage());
		} finally {
			if (pstmt != null) 
				pstmt.close();
			if (rs != null) 
				rs.close();
			con.close();
		}
		return resultlist;
	}
}
				