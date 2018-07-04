package com.techelevator;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import com.techelevator.model.Park;
import com.techelevator.model.JDBC.JDBCParkDAO;


public class JDBCParkDAOIntegrationTest extends DAOIntegrationTest {
	
	private JDBCParkDAO dao;
	private JdbcTemplate jdbcTemplate;
	
	@Before
	public void setup() {
		dao = JDBCParkDAO.instance.setDataSource(super.getDataSource());
		jdbcTemplate = new JdbcTemplate(super.getDataSource());
	}
	
	@Test
	public void getAllParksTest() {
		List<Park> allParks = dao.getAllParks();
		Assert.assertEquals(3,allParks.size());
		
		String sqlInsertPark = "INSERT INTO park(name, location, establish_date, area, visitors, description) VALUES (?,?,?,?,?,?)";
		Park firstPark = allParks.get(0);
		Assert.assertEquals("Acadia", firstPark.getName());
		jdbcTemplate.update(sqlInsertPark, "Abbot", firstPark.getLocation(), firstPark.getEstablishDate(), firstPark.getArea(), firstPark.getVisitors(), firstPark.getDescription());
		
		allParks = dao.getAllParks();
		Assert.assertEquals("Abbot", allParks.get(0).getName());
		Assert.assertEquals(4,allParks.size());
	}
}
