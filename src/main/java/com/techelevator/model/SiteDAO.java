package com.techelevator.model;

import java.time.LocalDate;
import java.util.List;

public interface SiteDAO extends DAO {
	
	/**
	 * Get all sites that are available at the campground.
	 * 
	 * @param campground the campground we are looking for available sites in
	 * @param startDate the start date of the desired reservation
	 * @param endDate the end date of the desired reservation
	 * @param rows the numbers of rows desired, 0 shows all 
	 * @return all of the available sites in a list
	 */
	public List<Site> getAvailableSites(Campground campground, LocalDate startDate, LocalDate endDate, int rows);
	
}
