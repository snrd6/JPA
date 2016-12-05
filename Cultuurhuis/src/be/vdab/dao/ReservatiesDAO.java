package be.vdab.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.SQLException;



public class ReservatiesDAO extends AbstractDAO 
{

	private static final String CREATE_RESERVATIE_SQL = "insert into reservaties (klantid, voorstellingsid, plaatsen) values(?, ?, ?)";
	private static final String REDUCE_PLAATSEN_SQL = "update voorstellingen set vrijeplaatsen =vrijeplaatsen- ? where id = ?";

	
	
	public boolean boekReservaties(long klantId, long voorstellingsId, int plaatsen){
		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement1 = connection.prepareStatement(CREATE_RESERVATIE_SQL);
				PreparedStatement statement2 = connection.prepareStatement(REDUCE_PLAATSEN_SQL)	)
		{
			connection.setTransactionIsolation(java.sql.Connection.TRANSACTION_SERIALIZABLE);
			connection.setAutoCommit(false);
			
	
			
		
					
					statement1.setLong(1, klantId); 
					statement1.setLong(2, voorstellingsId);
					statement1.setInt(3, plaatsen);
					statement1.executeUpdate();
					
					
				
					statement2.setInt(1, plaatsen);
					statement2.setLong(2, voorstellingsId);
					statement2.executeUpdate();
					
					
		
				connection.commit();
			
		}
		catch (SQLException ex)
		{
			return false;
		}
		return true;
	}
	
	
}
