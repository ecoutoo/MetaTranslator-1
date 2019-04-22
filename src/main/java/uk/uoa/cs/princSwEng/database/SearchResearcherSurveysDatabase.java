package uk.uoa.cs.princSwEng.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import uk.uoa.cs.princSwEng.resource.Global;
import uk.uoa.cs.princSwEng.resource.Survey;

public class SearchResearcherSurveysDatabase {
	private static final String STATEMENT = "SELECT id FROM SURVEYS WHERE user_id=?";
	private final Connection con;
	private final int researcher;
	
	public SearchResearcherSurveysDatabase(final Connection con, int researcher) {
		this.con = con;
		this.researcher = researcher;
	}
	
	public int[] searchResearcherSurveys() throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int[] survarr = null;
		List<Integer> survlist = new ArrayList<Integer>();
		int count;
		
		try {
			pstmt = con.prepareStatement(STATEMENT);
			pstmt.setInt(1,researcher);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				survlist.add(rs.getInt("id"));
			}
			count = survlist.size();
			survarr = new int[count];
			for (int i=0; i<count; i++) {
				survarr[i] = survlist.get(i);
			}
		} catch(SQLException ex) {
			System.err.println("SQLException invoked by the try block in SearchResearcherSurveysDatabase");
			System.err.println(ex.getMessage());
		} finally {
			if (pstmt != null)
				pstmt.close();
			if (rs != null)
				rs.close();
			con.close();
		}
		return survarr;
	}
}

