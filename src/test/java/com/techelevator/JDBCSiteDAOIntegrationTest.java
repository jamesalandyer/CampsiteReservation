package com.techelevator;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.techelevator.model.Campground;
import com.techelevator.model.Site;
import com.techelevator.model.JDBC.JDBCSiteDAO;

public class JDBCSiteDAOIntegrationTest extends DAOIntegrationTest {

	private JDBCSiteDAO dao;
	
	@Before
	public void setup() {
		dao = JDBCSiteDAO.instance.setDataSource(super.getDataSource());
	}
	
	@Test
	public void getAvailableSitesTest() {
		Campground newCampground = new Campground(1,1, "Blackwoods", "01", "12", new BigDecimal(35.00));
		List<Site> siteList = dao.getAvailableSites(newCampground, LocalDate.parse("2018-06-26"), LocalDate.parse("2018-06-30"), 0);
		Assert.assertEquals(258, siteList.size());
		
		Campground newCampground1 = new Campground(7,3, "The Unnamed Primitive Campsites", "05", "11", new BigDecimal(20.00));
		List<Site> siteList1 = dao.getAvailableSites(newCampground1, LocalDate.parse("2018-06-26"), LocalDate.parse("2018-06-30"), 0);
		Assert.assertEquals(5, siteList1.size());
		
		Campground newCampground2 = new Campground(7,3, "The Unnamed Primitive Campsites", "08", "11", new BigDecimal(20.00));
		List<Site> siteList2 = dao.getAvailableSites(newCampground2, LocalDate.parse("2018-06-26"), LocalDate.parse("2018-06-30"), 0);
		Assert.assertEquals(0, siteList2.size());
		
		Campground newCampground3 = new Campground(1,1, "Blackwoods", "01", "12", new BigDecimal(35.00));
		List<Site> siteList3 = dao.getAvailableSites(newCampground3, LocalDate.parse("2018-06-26"), LocalDate.parse("2018-06-30"), 5);
		Assert.assertEquals(5, siteList3.size());
	}

}
