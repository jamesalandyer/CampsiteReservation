package com.techelevator.model.JDBC;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.model.Campground;
import com.techelevator.model.Site;
import com.techelevator.model.SiteDAO;

public final class JDBCSiteDAO implements SiteDAO {
	
	public final static JDBCSiteDAO instance = new JDBCSiteDAO();
	
	private JdbcTemplate jdbcTemplate;
	
	private JDBCSiteDAO() {}
	
	@Override
	public JDBCSiteDAO setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		return this;
	}
	
	@Override
	public List<Site> getAvailableSites(Campground campground, LocalDate startDate, LocalDate endDate, int rows) {
		List<Site> siteList = new ArrayList<>();
		
		if (validateReservationDates(campground, startDate, endDate)) { 
			String sqlAvailableSites = "SELECT site.site_id, site.campground_id, site_number, max_occupancy, accessible, max_rv_length, utilities FROM site " + 
					"JOIN campground ON campground.campground_id = site.campground_id " + 
					"WHERE site.campground_id = ? " + 
					"EXCEPT " + 
					"SELECT site.site_id, site.campground_id, site_number, max_occupancy, accessible, max_rv_length, utilities FROM site " + 
					"JOIN campground ON campground.campground_id = site.campground_id " + 
					"JOIN reservation ON site.site_id = reservation.site_id " + 
					"WHERE site.campground_id = ? " + 
					"AND ((? <= reservation.to_date AND ? >= reservation.from_date) " + 
					"OR (? <= reservation.to_date AND ? >= reservation.from_date) " + 
					"OR (? <= reservation.from_date AND ? >= reservation.to_date)) ";
					
			SqlRowSet results;
			if (rows != 0) {
				sqlAvailableSites += "LIMIT ?";
				results = jdbcTemplate.queryForRowSet(sqlAvailableSites, campground.getId(), campground.getId(), 
						startDate, startDate, endDate, endDate, startDate, endDate, rows);
			} else {
				results = jdbcTemplate.queryForRowSet(sqlAvailableSites, campground.getId(), campground.getId(), 
						startDate, startDate, endDate, endDate, startDate, endDate);
			}
			
			while(results.next()) {
				siteList.add(mapSite(results));
			}
		}
		
		return siteList;
	}
	
	/**
	 * Validates that a reservation can be made based on the dates.
	 * 
	 * @param campground the campground where the reservation is being made
	 * @param startDate the start date of the reservation
	 * @param endDate the end date of the reservation
	 * @return true if the dates are valid
	 */
	private boolean validateReservationDates(Campground campground, LocalDate startDate, LocalDate endDate) {
		Integer startDateMonth = startDate.getMonth().getValue();
		Integer endDateMonth = endDate.getMonth().getValue();
		Integer campgroundOpenFromMonth = Integer.parseInt(campground.getOpenFromMM());
		Integer campgroundOpenToMonth = Integer.parseInt(campground.getOpenToMM());
		
		boolean isStartDateAfterOpen = startDateMonth >= campgroundOpenFromMonth;
		boolean isStartDateBeforeClosed = startDateMonth <= campgroundOpenToMonth;
		boolean isEndDateAfterOpen = endDateMonth >= campgroundOpenFromMonth;
		boolean isEndDateBeforeClosed = endDateMonth <= campgroundOpenToMonth;
		boolean isOpenYearRound = (campgroundOpenFromMonth == 1 && campgroundOpenToMonth == 12) || (campgroundOpenFromMonth - campgroundOpenToMonth == 1);
		boolean isOverlappingYears = startDate.getYear() != endDate.getYear();
		boolean parkStaysOpen = (isOverlappingYears) ? isOpenYearRound : true;
		
		return isStartDateAfterOpen && isStartDateBeforeClosed && isEndDateAfterOpen && isEndDateBeforeClosed && parkStaysOpen;
	}
	
	/**
	 * Maps a query result to a Site.
	 * 
	 * @param results the results you want to map into a Site
	 * @return the site that was created from results
	 */
	private Site mapSite(SqlRowSet results) {
		long id = results.getLong("site_id");
		long campgroundId = results.getLong("campground_id");
		int siteNumber = results.getInt("site_number");
		int maxOccupancy = results.getInt("max_occupancy");
		boolean accessible = results.getBoolean("accessible");
		int maxRVLength = results.getInt("max_rv_length");
		boolean utilities = results.getBoolean("utilities");
		
		return new Site(id, campgroundId, siteNumber, maxOccupancy, accessible, maxRVLength, utilities);
	}
}
