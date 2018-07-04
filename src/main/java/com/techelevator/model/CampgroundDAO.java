package com.techelevator.model;

import java.util.List;

public interface CampgroundDAO extends DAO {

	/**
	 * Get all the campgrounds for a specific park.
	 * 
	 * @param park the park for the campgrounds 
	 * @return all of the campgrounds for the park in a list
	 */
	public List<Campground> getAllCampgroundsForPark(Park park);
	
}
