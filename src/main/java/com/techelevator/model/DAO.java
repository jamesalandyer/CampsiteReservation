package com.techelevator.model;

import javax.sql.DataSource;

public interface DAO {

	/**
	 * Set the data source for the dao.
	 * 
	 * @param dataSource the source of the data for the dao
	 * @return the object that the data source was set for
	 */
	public DAO setDataSource(DataSource dataSource);
	
}
