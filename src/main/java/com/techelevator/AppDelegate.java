package com.techelevator;

import java.io.PrintWriter;
import java.util.Scanner;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

import com.techelevator.controller.NavigationController;
import com.techelevator.controller.ViewController.ParksViewController;
import com.techelevator.model.JDBC.JDBCCampgroundDAO;
import com.techelevator.model.JDBC.JDBCParkDAO;
import com.techelevator.model.JDBC.JDBCReservationDAO;
import com.techelevator.model.JDBC.JDBCSiteDAO;

public final class AppDelegate {
	
	public static PrintWriter out;
	public static Scanner in;

	public static void main(String[] args) {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/campground");
		dataSource.setUsername("postgres");
		
		AppDelegate application = new AppDelegate(dataSource);
		application.run();
		out.close();
		in.close();
	}

	/**
	 * Sets up the application.
	 * 
	 * @param dataSource the database to use
	 */
	public AppDelegate(DataSource dataSource) {
		out = new PrintWriter(System.out);
		in = new Scanner(System.in);
		JDBCSiteDAO.instance.setDataSource(dataSource);
		JDBCParkDAO.instance.setDataSource(dataSource);
		JDBCReservationDAO.instance.setDataSource(dataSource);
		JDBCCampgroundDAO.instance.setDataSource(dataSource);
	}
	
	/**
	 * Starts the application up.
	 */
	private void run() {
		NavigationController.instance.pushViewController(new ParksViewController(), null);	
	}
}
