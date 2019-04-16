package uk.uoa.cs.princSwEng.database;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import uk.uoa.cs.princSwEng.resource.Global;

import uk.uoa.cs.princSwEng.resource.Survey;

public class SearchSurveyDatabase
{

	private static final String STATEMENT = "SELECT * FROM SURVEYS WHERE id=?";
	private final Connection con;
	private final String idsurv;

	//Survey ssd = new SearchSurveyDatabase(getConnection(), key).SearchSurvey(); (As called from index)
	public SearchSurveyDatabase(final Connection con, String survey)
	{
		//survey == id correct
		this.con = con;
		this.idsurv = survey;
	}

	public Survey SearchSurvey() throws SQLException
	{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Survey sur = null;

		try
		{
			pstmt = con.prepareStatement(STATEMENT);
			pstmt.setString(1,idsurv);
			rs = pstmt.executeQuery();

			if (rs.next())
			{
				int num = rs.getInt("num");
				int[] sent = new int[num];
				
				for (int i=0; i<num;i++)
				{
					String senti = "sent" + Integer.toString(i+1);
					sent[i] = rs.getInt(senti);					
				}

				//sur = new Survey(rs.getString("corpora"), rs.getString("translator"), rs.getString("languages"), num, sent, rs.getString("rkey"));
				try {
					sur = new Survey(rs.getString("corpora"), rs.getString("translator"), rs.getString("languages"), num, sent);
				}
				catch(SQLException ex) {
					System.err.println("error when creating new Survey");
				}
			}
		}
		catch(SQLException ex)
		{
			System.err.println("SQLException invoked by the try block in SearchSurveyDatabase");
			System.err.println(ex.getMessage());
		}
		finally
		{
			if (pstmt != null)
				pstmt.close();
			if (rs != null)
				rs.close();
			con.close();
		}
		return sur;

	}
}