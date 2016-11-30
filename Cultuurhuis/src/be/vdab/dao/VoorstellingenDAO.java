package be.vdab.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import be.vdab.entities.Voorstelling;

public class VoorstellingenDAO extends AbstractDAO
{

	private static final String FIND_VOORSTELLINGEN_BY_GENRE_SQL = 
			"select * from voorstellingen as v inner join genres as g on v.genreid = g.id and v.datum > {fn curdate()} "
			+ "and g.naam = ?";
	
	private static final String FIND_VOORSTELLING_SQL = 
			"select * from voorstellingen where id = ?"; 
	private final static Logger logger=Logger.getLogger(VoorstellingenDAO.class.getName());
	
	public List<Voorstelling> findVoorstellingenByGenre(String genreNaam)
	{
		try(	Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(FIND_VOORSTELLINGEN_BY_GENRE_SQL))
		{
			List<Voorstelling> voorstellingen = new ArrayList<Voorstelling>();
			
			statement.setString(1, genreNaam);
			
			try(ResultSet resultSet = statement.executeQuery())
			{
				while(resultSet.next())
				{
					voorstellingen.add(resultSetRijNaarVoorstelling(resultSet));
				}
				return voorstellingen;
			}
		} 
		catch(SQLException ex) 
		{
			logger.log(Level.SEVERE,"Probleem met databaseverbinding",ex);
			throw new DAOException(ex);
		}
	}
	
	//
	public Voorstelling findVoorstelling(long voorstellingId)
	{
		Voorstelling voorstelling = null;
		
		try(	Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(FIND_VOORSTELLING_SQL);
			)
		{
			statement.setLong(1, voorstellingId);
			
			try(ResultSet resultSet = statement.executeQuery())
			{
				if(resultSet.next())
				{
					voorstelling = new Voorstelling(resultSet.getLong("id"), resultSet.getString("titel"), resultSet.getString("uitvoerders"),
					resultSet.getTimestamp("datum"), resultSet.getLong("genreid"), resultSet.getBigDecimal("prijs"), resultSet.getInt("vrijeplaatsen"));
				}
			}
		}
		catch(SQLException ex)
		{
			logger.log(Level.SEVERE,"Probleem met databaseverbinding",ex);
			throw new DAOException(ex);
		}
		return voorstelling;
	}
	
	
	private Voorstelling resultSetRijNaarVoorstelling(ResultSet resultSet) throws SQLException 
	{
		return new Voorstelling(
			resultSet.getInt("id"), 
			resultSet.getString("titel"),
			resultSet.getString("uitvoerders"),
			resultSet.getTimestamp("datum"),
			resultSet.getInt("genreId"),
			resultSet.getBigDecimal("prijs"),
			resultSet.getInt("vrijeplaatsen"));
	}
}
