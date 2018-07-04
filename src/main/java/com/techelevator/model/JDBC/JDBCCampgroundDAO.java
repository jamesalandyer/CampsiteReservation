package com.techelevator.model.JDBC;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.model.Campground;
import com.techelevator.model.CampgroundDAO;
import com.techelevator.model.Park;

public final class JDBCCampgroundDAO implements CampgroundDAO {

	public final static JDBCCampgroundDAO instance = new JDBCCampgroundDAO();
	
	private JdbcTemplate jdbcTemplate;
	
	private JDBCCampgroundDAO() {}
	
	@Override
	public JDBCCampgroundDAO setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		return this;
	}
	
	@Override
	public List<Campground> getAllCampgroundsForPark(Park park) {
		List<Campground> allCampgrounds = new ArrayList<>();
		String sqlAllCampgrounds = "SELECT * FROM campground WHERE park_id = ?";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlAllCampgrounds, park.getId());
		while (results.next()) {
			allCampgrounds.add(mapCampground(results));
		}
		
		return allCampgrounds;
	}

	/**
	 * Maps a query result to a Campground.
	 * 
	 * @param results the results you want to map into a Campground
	 * @return the campground that was created from results
	 */
	private Campground mapCampground(SqlRowSet results) {
		long id = results.getLong("campground_id");
		long park_id = results.getLong("park_id");
		String name = results.getString("name");
		String openFromMM = results.getString("open_from_mm");
		String openToMM = results.getString("open_to_mm");
		BigDecimal dailyFee = results.getBigDecimal("daily_fee");
		
		return new Campground(id, park_id, name, openFromMM, openToMM, dailyFee);
	}

}
