package com.techelevator.model;

import java.util.List;

public interface ParkDAO extends DAO {
	
	/**
	 * Get all parks from the datastore (sorted alphabetically).
	 * 
	 * @return all parks as Park objects in List
	 */
	public List<Park> getAllParks();
	
}
