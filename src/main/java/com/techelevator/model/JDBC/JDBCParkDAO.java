package com.techelevator.model.JDBC;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.model.Park;
import com.techelevator.model.ParkDAO;

public final class JDBCParkDAO implements ParkDAO {

	public final static JDBCParkDAO instance = new JDBCParkDAO();
	
	private JdbcTemplate jdbcTemplate;
	
	private JDBCParkDAO() {}
	
	@Override
	public JDBCParkDAO setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		return this;
	}
	
	@Override
	public List<Park> getAllParks() {
		List<Park> allParks = new ArrayList<>();
		String sqlAllParks = "SELECT * FROM park ORDER BY name";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlAllParks);
		while (results.next()) {
			allParks.add(mapPark(results));
		}
		
		return allParks;
	}

	/**
	 * Maps a query result to a Park.
	 * 
	 * @param results the results you want to map into a Park
	 * @return the park that was created from results
	 */
	private Park mapPark(SqlRowSet results) {
		long id = results.getLong("park_id");
		String name = results.getString("name");
		String location = results.getString("location");
		LocalDate establishDate = results.getDate("establish_date").toLocalDate();
		int area = results.getInt("area");
		int visitors = results.getInt("visitors");
		String description = results.getString("description");
		
		return new Park(id, name, location, establishDate, area, visitors, description);
	}

}
