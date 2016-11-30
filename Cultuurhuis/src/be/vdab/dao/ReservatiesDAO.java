package be.vdab.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReservatiesDAO extends AbstractDAO 
{
	private static final String SELECT_VRIJE_PLAATSEN_SQL = "select vrijeplaatsen from voorstellingen where id = ?";
	private static final String CREATE_RESERVATIE_SQL = "insert into reservaties (klantid, voorstellingsid, plaatsen) values(?, ?, ?)";
	private static final String REDUCE_PLAATSEN_SQL = "update voorstellingen set vrijeplaatsen =vrijeplaatsen- ? where id = ?";
	private final static Logger logger=Logger.getLogger(ReservatiesDAO.class.getName());
	
	
	public boolean boekReservaties(long klantId, long voorstellingsId, int plaatsen){
		try (Connection connection = dataSource.getConnection())
		{
			connection.setTransactionIsolation(java.sql.Connection.TRANSACTION_SERIALIZABLE);
			connection.setAutoCommit(false);
			
			int vrijePlaatsen = getVrijePlaatsen(voorstellingsId);
			
			if ((vrijePlaatsen - plaatsen) < 0)
			{
				return false;
			}
			else 
			{
				try (	PreparedStatement statement1 = connection.prepareStatement(CREATE_RESERVATIE_SQL);
						PreparedStatement statement2 = connection.prepareStatement(REDUCE_PLAATSEN_SQL)
					)
				{
					
					statement1.setLong(1, klantId); 
					statement1.setLong(2, voorstellingsId);
					statement1.setInt(3, plaatsen);
					statement1.executeUpdate();
					
					
					int resultPlaatsen = vrijePlaatsen - plaatsen;
					statement2.setInt(1, resultPlaatsen);
					statement2.setLong(2, voorstellingsId);
					statement2.executeUpdate();
					
					
				}
				connection.commit();
			}
		}
		catch (SQLException ex)
		{
			logger.log(Level.SEVERE,"Probleem met databaseverbinding",ex);
			throw new DAOException(ex);
		}
		return true;
	}
	
	private int getVrijePlaatsen(long voorstellingsId)
	{
		int vrijePlaatsen = 0;
		
		try (	Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(SELECT_VRIJE_PLAATSEN_SQL);
			)
		{
			statement.setLong(1, voorstellingsId);
			
			try (	ResultSet resultSet = statement.executeQuery())
			{
				if (resultSet.first())
				{
					vrijePlaatsen = resultSet.getInt(1);
				}
			}
			return vrijePlaatsen;
		}
		catch(SQLException ex)
		{
			logger.log(Level.SEVERE,"Probleem met databaseverbinding",ex);
			throw new DAOException(ex);
		}
	}
}
