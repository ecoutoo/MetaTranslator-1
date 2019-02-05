package uk.uoa.cs.princSwEng.database;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import uk.uoa.cs.princSwEng.resource.Global;

import uk.uoa.cs.princSwEng.resource.Survey;
import uk.uoa.cs.princSwEng.resource.Sentence;

public class SearchSentencesFromSurveyDatabase
{
	private static final String STATEMENT = "SELECT * FROM SENTENCES WHERE id=?";
	private final Connection con;
	private final Survey survey;

	public SearchSentencesFromSurveyDatabase(final Connection con, Survey survey)
	{
		this.con = con;
		this.survey = survey;
	}

	public List<Sentence> SearchSentencesFromSurvey() throws SQLException
	{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int num = survey.getSurveyNum();
		List<Sentence> sent = new ArrayList<Sentence>();
		int[] sentId = survey.getSurveyId();

		try
		{
			pstmt = con.prepareStatement(STATEMENT);
			for (int i=0;i<num; i++)
			{
				// if (sentId[i]!=null)
			// {
				if (Global.DEBUGMODE)
					System.out.println("Sent_id value is: " + sentId[i]);
				pstmt.setInt(1,sentId[i]);
				rs = pstmt.executeQuery();
				if (rs.next())
				{
					int id = rs.getInt("id");
					String internal_id = rs.getString("internal_id");
					String sentence = rs.getString("sentence");
					System.out.println("id, internal_id, sentence: ");
					if (Global.DEBUGMODE) 
					{
						System.out.println(id);
						System.out.println(internal_id);
						System.out.println(sentence);
					}
					sent.add(new Sentence(id,internal_id,sentence));
				}					
//				}

			}
			
		}
		catch(SQLException ex)
		{
			System.err.println("SQLException invoked by the try block in SearchSentencesFromSurveyDatabase");
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
		return sent;

	}
}