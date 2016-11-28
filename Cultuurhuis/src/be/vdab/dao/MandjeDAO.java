package be.vdab.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import be.vdab.entities.MandjeItem;
import be.vdab.entities.Voorstelling;

public class MandjeDAO extends AbstractDAO
{
	private static final String FIND_VOORSTELLING_SQL = "select * from voorstellingen where id = ?";
	private final static Logger logger=Logger.getLogger(MandjeDAO.class.getName());
	
	public Iterable<MandjeItem> geefMandje(Map<Long, Integer> mandjeMap)
	{
		List<MandjeItem> mandjeItemsList = new ArrayList<>();
		

		for(Map.Entry<Long, Integer> entry : mandjeMap.entrySet())
		{
			
			long voorstellingId = entry.getKey();
	
			int aantalPlaatsen = entry.getValue();
			
			try(	Connection connection = dataSource.getConnection();
					PreparedStatement statement = connection.prepareStatement(FIND_VOORSTELLING_SQL);
				)
			{
				statement.setLong(1,voorstellingId);
				
				try(ResultSet resultSet = statement.executeQuery())
				{
					if(resultSet.first())
					{
						Voorstelling voorstelling = new Voorstelling(resultSet.getLong("id"), resultSet.getString("titel"), resultSet.getString("uitvoerders"),
						resultSet.getTimestamp("datum"), resultSet.getLong("genreid"), resultSet.getBigDecimal("prijs"), resultSet.getInt("vrijeplaatsen"));
						MandjeItem mandjeItem = new MandjeItem(voorstelling, aantalPlaatsen);
						mandjeItemsList.add(mandjeItem); 	
					}
				}
			}
			catch(SQLException ex)
			{
				logger.log(Level.SEVERE,"Probleem met databaseverbinding",ex);
				throw new DAOException(ex);
			}
		}
		return mandjeItemsList;
	}
}
