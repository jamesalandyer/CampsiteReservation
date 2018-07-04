package com.techelevator.controller.ViewController;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.techelevator.controller.NavigationController;
import com.techelevator.model.Campground;
import com.techelevator.model.Park;
import com.techelevator.model.JDBC.JDBCCampgroundDAO;
import com.techelevator.view.SimpleView;
import com.techelevator.view.MenuView.InputMenuView.DateInputMenuView;
import com.techelevator.view.MenuView.InputMenuView.NumberInputMenuView;
import com.techelevator.view.TableView.CampgroundTableView;

public final class ReservationSearchViewController extends ViewController {
	
	private final String CAMPGROUND_PROMPT = "Which campground (enter a 0 to cancel)? ";
	private final String ARRIVAL_PROMPT = "What is the arrival date (mm/dd/yyyy)? ";
	private final String DEPART_PROMPT = "What is the departure date (mm/dd/yyyy)? ";
	private final LocalDate MAX_DATE = LocalDate.parse("2050-12-31");
	
	private Park selectedPark = null;
	private List<Campground> listOfCampgrounds;
	private Campground selectedCampground = null;
	
	private LocalDate startDate;
	private LocalDate endDate;
	
	@Override
	public void loadView(Object data) {
		
		if (selectedPark == null) {
			selectedPark = (Park) data;
		}
		
		showSearchTable();
		setSearchMenuResponse();	
		
		if (selectedCampground == null) {
			NavigationController.instance.popViewController();
			return;
		} else {
			List<Object> items = new LinkedList<>();
			items.add(selectedCampground);
			items.add(startDate);
			items.add(endDate);
			items.add(calculateReservationCost(startDate, endDate));
			
			NavigationController.instance.pushViewController(new ReservationViewController(), items);
		}	
		
	}
	
	/**
	 * Shows the search table.
	 */
	private void showSearchTable() {
		listOfCampgrounds = JDBCCampgroundDAO.instance.getAllCampgroundsForPark(selectedPark);
		CampgroundTableView campgroundTableView = new CampgroundTableView(listOfCampgrounds, selectedPark.getName() + " National Park Campgrounds Reservation");
		campgroundTableView.displayTableView();
	}
	
	/**
	 * Shows the search menu and sets the response.
	 */
	private void setSearchMenuResponse() {
		while(true) {
			Integer campgroundResponse = ((Integer) new NumberInputMenuView(CAMPGROUND_PROMPT).getResponseFromMenu()) - 1;
			
			if (campgroundResponse == -1) {
				NavigationController.instance.popViewController();
				return;
			}
			
			startDate = (LocalDate) new DateInputMenuView(ARRIVAL_PROMPT).getResponseFromMenu();
			endDate = (LocalDate) new DateInputMenuView(DEPART_PROMPT).getResponseFromMenu();
			
			if (startDate.equals(endDate)) {
				SimpleView dateError = new SimpleView("Arrival date cannot be the same as departure date. Please try again!");
				dateError.displayView();
				continue;
			} else if (startDate.isBefore(LocalDate.now())) {
				SimpleView pastDateError = new SimpleView("Arrival date cannot be before today. Please try again!");
				pastDateError.displayView();
				continue;
			} else if (startDate.isAfter(endDate)) {
				SimpleView illegalDateError = new SimpleView("Arrival date cannot be after departure date. Please try again!");
				illegalDateError.displayView();
				continue;
			} else if (startDate.isAfter(MAX_DATE) || endDate.isAfter(MAX_DATE)) {
				SimpleView maxDateError = new SimpleView("Your reservation dates are too far into the future. Please try again!");
				maxDateError.displayView();
				continue;
			}
			
			if(campgroundResponse < listOfCampgrounds.size()) {
				selectedCampground = listOfCampgrounds.get(campgroundResponse);
				break;
			} else {
				SimpleView invalidError = new SimpleView("Invalid campground. Please try again!");
				invalidError.displayView();
			}
		}
	}
	
	/**
	 * Calculates the cost of the reservation.
	 * 
	 * @param startDate the desired reservation start date
	 * @param endDate the desired reservation end date
	 * @return the total cost
	 */
	private BigDecimal calculateReservationCost(LocalDate startDate, LocalDate endDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date firstDate = null;
		Date secondDate = null;
		
		try {
			firstDate = formatter.parse(startDate.toString());
			secondDate = formatter.parse(endDate.toString());
		} catch (ParseException e) {
			SimpleView dateError = new SimpleView("Invalid dates. Please try again!");
			dateError.displayView();
		}
		
		long diffInMillies = Math.abs(secondDate.getTime() - firstDate.getTime());
		long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
		
		BigDecimal totalCost = selectedCampground.getDailyFee().multiply(new BigDecimal(diff));
		
		return totalCost;
	}
}
