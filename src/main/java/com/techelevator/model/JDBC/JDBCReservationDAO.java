package com.techelevator.model.JDBC;

import java.time.LocalDate;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.model.Reservation;
import com.techelevator.model.ReservationDAO;
import com.techelevator.model.Site;

public final class JDBCReservationDAO implements ReservationDAO {

	public final static JDBCReservationDAO instance = new JDBCReservationDAO();
	
	private JdbcTemplate jdbcTemplate;
	
	private JDBCReservationDAO() {}
	
	@Override
	public JDBCReservationDAO setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		return this;
	}
	
	@Override
	public Reservation makeReservation(Site site, String name, LocalDate startDate, LocalDate endDate) {
		Reservation newReservation = new Reservation(getNextReservationId(), site.getId(), name, startDate, endDate, LocalDate.now());
		String sqlMakeReservation = "INSERT INTO reservation VALUES"
				+ "(?, ?, ?, ?, ?, ?)";
		jdbcTemplate.update(sqlMakeReservation, newReservation.getReservationId(), newReservation.getSiteId(), newReservation.getName(), newReservation.getFromDate(),
				newReservation.getToDate(), newReservation.getCreateDate());
		
		return newReservation;
	}
	
	/**
	 * Gets the next id for a reservation.
	 * 
	 * @return the long of the next reservation id
	 */
	private long getNextReservationId() {
		String sqlGetNextId = "SELECT nextval('reservation_reservation_id_seq')";
		SqlRowSet getNextId = jdbcTemplate.queryForRowSet(sqlGetNextId);
		getNextId.next();
		
		return getNextId.getLong(1);
	}
}
