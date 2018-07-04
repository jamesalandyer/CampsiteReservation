package com.techelevator.model;

import java.time.LocalDate;

public interface ReservationDAO extends DAO {
	
	/**
	 * Makes a reservation for the site.
	 * 
	 * @param site the site we are making the reservation at
	 * @param name the name of the person making the reservation
	 * @param startDate the start date of the desired reservation
	 * @param endDate the end date of the desired reservation
	 * @return the reservation made
	 */
	public Reservation makeReservation(Site site, String name, LocalDate startDate, LocalDate endDate);
	
}
