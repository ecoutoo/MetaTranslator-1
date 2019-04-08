package uk.uoa.cs.princSwEng.database;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import uk.uoa.cs.princSwEng.resource.Global;

import uk.uoa.cs.princSwEng.resource.Researcher;

public class SearchResearcherDatabase
{
	private static final String STATEMENT = "SELECT * FROM USERS WHERE id=?";
	private final Connection con;
	private final int idresea;

	public SearchResearcherDatabase(final Connection con, int researcher)
	{
		this.con = con;
		this.idresea = researcher;
	}

	public Researcher SearchResearcher() throws SQLException
	{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Researcher rsc = null;

		try
		{
			pstmt = con.prepareStatement(STATEMENT);
			pstmt.setInt(1,idresea);
			rs = pstmt.executeQuery();
			if (rs.next())
			{
				rsc = new Researcher(rs.getString("username"),rs.getString("firstname"), rs.getString("surname"), rs.getString("email"), rs.getString("password"));
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
		return rsc;
	}
}