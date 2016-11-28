package be.vdab.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GenresDAO extends AbstractDAO
{	
	private static final String FIND_ALL_GENRES_SQL = "select naam from genres order by naam";
	private final static Logger logger=Logger.getLogger(GenresDAO.class.getName());
	
	public Iterable<String> findAllGenres()
	{
		try (Connection connection = dataSource.getConnection();
			 Statement statement = connection.createStatement();
			 ResultSet resultSet = statement.executeQuery(FIND_ALL_GENRES_SQL)) 
		{
			List<String> genres = new ArrayList<>();
			
			while(resultSet.next())
			{
				genres.add(resultSet.getString("naam"));
			}
			return genres;
		} 
		catch(SQLException ex) 
		{
			logger.log(Level.SEVERE,"Probleem met databaseverbinding",ex);
			throw new DAOException(ex);
		}
	}
}
