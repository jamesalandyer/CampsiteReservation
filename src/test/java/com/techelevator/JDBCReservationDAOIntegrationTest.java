package com.techelevator;

import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.model.Reservation;
import com.techelevator.model.Site;
import com.techelevator.model.JDBC.JDBCReservationDAO;

public class JDBCReservationDAOIntegrationTest extends DAOIntegrationTest {
	
	private JDBCReservationDAO dao;
	private JdbcTemplate jdbcTemplate;
	
	@Before
	public void setup() {
		dao = JDBCReservationDAO.instance.setDataSource(super.getDataSource());
		jdbcTemplate = new JdbcTemplate(super.getDataSource());
	}
	
	@Test
	public void makeReservationTest() {
				
		Site site = new Site(1, 1, 4, 2, true, 5, true);
		Reservation newReservation = dao.makeReservation(site, "Mason", LocalDate.parse("2018-05-05"), LocalDate.parse("2018-05-05"));
		
		Assert.assertEquals("Mason", newReservation.getName());
		
		String sqlGetReservation = "SELECT * FROM reservation WHERE reservation_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetReservation, newReservation.getReservationId());
		results.next();
		Assert.assertTrue(results.getLong("reservation_id") == newReservation.getReservationId());
	}

}
