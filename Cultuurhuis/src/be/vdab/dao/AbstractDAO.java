package be.vdab.dao;

import javax.sql.DataSource;

abstract class AbstractDAO 
{
	public final static String JNDI_NAME = "jdbc/cultuurhuis";
	protected static  DataSource dataSource;
	
	@SuppressWarnings("static-access")
	public void setDataSource(DataSource dataSource) 
	{
		this.dataSource = dataSource;
	}
}
