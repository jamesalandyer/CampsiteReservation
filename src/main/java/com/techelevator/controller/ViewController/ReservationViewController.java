package com.techelevator.controller.ViewController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.techelevator.controller.NavigationController;
import com.techelevator.model.Campground;
import com.techelevator.model.Reservation;
import com.techelevator.model.Site;
import com.techelevator.model.JDBC.JDBCReservationDAO;
import com.techelevator.model.JDBC.JDBCSiteDAO;
import com.techelevator.view.SimpleView;
import com.techelevator.view.MenuView.InputMenuView.InputMenuView;
import com.techelevator.view.MenuView.InputMenuView.NumberInputMenuView;
import com.techelevator.view.TableView.SiteTableView;

public final class ReservationViewController extends ViewController {

	private final String RESERVED_SITE_PROMPT = "Which site should be reserved (enter 0 to cancel)? ";
	private final String RESERVATION_NAME_PROMPT = "What name should the reservation be made under? ";
	
	private Campground selectedCampground;
	private List<Site> availableSites;
	private Site selectedSite = null;
	private LocalDate startDate;
	private LocalDate endDate;
	private BigDecimal totalCost;
	private String reservationName;
	
	@Override
	public void loadView(Object data) {
		setReservationDetails(data);
		showAvailableSites();
		setReservationMenuResponse();
		
		if (selectedSite != null) {
			makeReservation();
		}
		
		NavigationController.instance.popToFirst();
	}
	
	/**
	 * Sets the reservation details.
	 * 
	 * @param details details pertaining to the reservation
	 */
	@SuppressWarnings("unchecked")
	private void setReservationDetails(Object details) {
		List<Object> items = (List<Object>) details;
		selectedCampground = (Campground) items.get(0);
		startDate = (LocalDate) items.get(1);
		endDate = (LocalDate) items.get(2);
		totalCost = (BigDecimal) items.get(3);
	}
	
	/**
	 * Shows available sites.
	 */
	private void showAvailableSites() {
		availableSites = JDBCSiteDAO.instance.getAvailableSites(selectedCampground, startDate, endDate, 5);
		
		if (availableSites.isEmpty()) {
			SimpleView emptyMessage = new SimpleView("No sites available. Please enter different dates or choose another campground!");
			emptyMessage.displayView();
			NavigationController.instance.popViewController();
		} else {
			SiteTableView siteTable = new SiteTableView(availableSites, "Results Mathing Your Search Criteria", totalCost);
			siteTable.displayTableView();
		}
	}

	/**
	 * Shows the reservation menu and sets the response.
	 */
	private void setReservationMenuResponse() {
		while (true) {
			Integer reservedSite = (Integer) new NumberInputMenuView(RESERVED_SITE_PROMPT).getResponseFromMenu();
			
			if (reservedSite == 0) {
				NavigationController.instance.popViewController();
				return;
			}
			
			reservationName = (String) new InputMenuView(RESERVATION_NAME_PROMPT).getResponseFromMenu();
			
			for (Site site : availableSites) {
				if (site.getSiteNumber() == reservedSite) {
					selectedSite = site;
					break;
				}
			}
			
			if (selectedSite == null) {
				SimpleView siteError = new SimpleView("Invalid site number. Please try again!");
				siteError.displayView();
			} else {
				break;
			}
		}
	}
	
	/**
	 * Makes the reservation.
	 */
	private void makeReservation() {
		Reservation newReservation = JDBCReservationDAO.instance.makeReservation(selectedSite, reservationName, startDate, endDate);
		SimpleView reservationMessage = new SimpleView("The reservation has been made and the confirmation id is " + newReservation.getReservationId());
		reservationMessage.displayView();
	}
}
