package uk.uoa.cs.princSwEng.database;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import uk.uoa.cs.princSwEng.resource.Researcher;

public class CreateResearcherDatabase
{
	private static final String STATEMENT = "INSERT INTO USERS (username, password, firstname, surname, email) VALUES (?,?,?,?,?) RETURNING *";
	private final Connection con;
	private final Researcher resea;
	public CreateResearcherDatabase(final Connection con, Researcher researcher)
	{
		this.con = con;
		this.resea = researcher;
	}
	/**
	* Stores a new Category into the database
	*
	* @throws SQLException
	*             if any error occurs while storing the Category.
	*/
	public int createResearcher() throws SQLException
	{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = -1;
		try 
		{
			pstmt = con.prepareStatement(STATEMENT);
			pstmt.setString(1, resea.getResearcherUsername());
			pstmt.setString(2, resea.getResearcherPassword());
			pstmt.setString(3, resea.getResearcherName());
			pstmt.setString(4, resea.getResearcherSurname());
			pstmt.setString(5, resea.getResearcherEmail());
			System.err.println("Before executing executeQuery on CreateResearcherDatabase");
			rs = pstmt.executeQuery();
			if (rs.next())
			{
				result = rs.getInt("id");
			}
		}
		catch(SQLException ex)
		{
			System.err.println("SQLException invoked by the try block in CreateResearcherDatabase");
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
