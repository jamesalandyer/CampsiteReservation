package com.techelevator;

import java.time.LocalDate;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import com.techelevator.model.Campground;
import com.techelevator.model.Park;
import com.techelevator.model.JDBC.JDBCCampgroundDAO;


public class JDBCCampgroundDAOIntegrationTest extends DAOIntegrationTest {
	private JDBCCampgroundDAO dao;
	
	@Before
	public void setup() {
		dao = JDBCCampgroundDAO.instance.setDataSource(super.getDataSource());
	}
	
	@Test
	public void getAllCampgroundsForParkTest() {
		Park newPark = new Park(1, "", "", LocalDate.now(), 0, 0, "");
		List<Campground> campgroundList = dao.getAllCampgroundsForPark(newPark);
		Assert.assertEquals(3, campgroundList.size());
	}
	
}
