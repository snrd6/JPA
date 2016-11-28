package be.vdab.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import be.vdab.entities.Klant;

public class KlantDAO extends AbstractDAO
{
	private static final String FIND_KLANT_SQL = 
			"select * from klanten where gebruikersnaam = ? and paswoord = ?";
	private static final String NIEUWE_KLANT_SQL = 
			"insert into klanten (voornaam, familienaam, straat, huisnr, postcode, gemeente, gebruikersnaam, paswoord) values(?,?,?,?,?,?,?,?)";
	private static final String CHECK_GEBRUIKERSNAAM_SQL = 
			"select gebruikersnaam from klanten where gebruikersnaam = ?";
	private final static Logger logger=Logger.getLogger(KlantDAO.class.getName());
	
	public Klant findKlant(String gebruikersnaam, String paswoord)
	{
		Klant klant = null;
		
		try(	Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(FIND_KLANT_SQL);
			)
		{
			statement.setString(1, gebruikersnaam);
			statement.setString(2, paswoord);
			
			try(ResultSet resultSet = statement.executeQuery())
			{
				if(resultSet.first())
				{
					klant = new Klant(resultSet.getInt("id"), resultSet.getString("voornaam"), resultSet.getString("familienaam"),
					resultSet.getString("straat"), resultSet.getString("huisnr"), resultSet.getString("postcode"),
					resultSet.getString("gemeente"), resultSet.getString("gebruikersnaam"), resultSet.getString("paswoord"));
				}
			}
		}
		catch(SQLException ex)
		{
			logger.log(Level.SEVERE,"Probleem met databaseverbinding",ex);
			throw new DAOException(ex);
		}
		return klant;
	}
	
	public void createKlant(Klant klant)
	{
		try(	Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(NIEUWE_KLANT_SQL, Statement.RETURN_GENERATED_KEYS);
			)
		{
			statement.setString(1, klant.getVoornaam());
			statement.setString(2, klant.getFamilienaam());
			statement.setString(3, klant.getStraat());
			statement.setString(4, klant.getHuisNr());
			statement.setString(5, klant.getPostCode());
			statement.setString(6, klant.getGemeente());
			statement.setString(7, klant.getGebruikersnaam());
			statement.setString(8, klant.getPaswoord());
			statement.executeUpdate();
			
			try (ResultSet resultSet = statement.getGeneratedKeys())
			{
				if (!resultSet.next())
				{
					throw new DAOException("Kan geen verbinding maken met database");
				}
				klant.setKlantId(resultSet.getInt(1));
			}
		}
		catch(SQLException ex)
		{
			logger.log(Level.SEVERE,"Probleem met databaseverbinding",ex);
			throw new DAOException(ex);
		}
	}
	

	public boolean checkGebruikersnaamUniciteit(String gebruikersnaam)
	{
		try(	Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(CHECK_GEBRUIKERSNAAM_SQL);
			)
		{
			statement.setString(1, gebruikersnaam);
			try(ResultSet resultSet = statement.executeQuery())
			{
				return resultSet.next();
			}
		}
		catch(SQLException ex){
			logger.log(Level.SEVERE,"Probleem met databaseverbinding",ex);
			throw new DAOException(ex);
		}
	}
}