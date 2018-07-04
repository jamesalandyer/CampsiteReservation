package com.techelevator.controller.ViewController;

import java.util.LinkedList;
import java.util.List;

import com.techelevator.controller.NavigationController;
import com.techelevator.model.Campground;
import com.techelevator.model.Park;
import com.techelevator.model.JDBC.JDBCCampgroundDAO;
import com.techelevator.view.MenuView.ChoiceMenuView.ChoiceMenuView;
import com.techelevator.view.TableView.CampgroundTableView;

public final class CampgroundsViewController extends ViewController{
	
	private Park selectedPark = null;
	
	@Override
	public void loadView(Object data) {
		
		if (selectedPark == null) {
			selectedPark = (Park) data;
		}
		
		showCampgrounds();
		Integer campgroundResponse = getCampgroundsMenuResponse();

		if (campgroundResponse == 0) {
			NavigationController.instance.pushViewController(new ReservationSearchViewController(), selectedPark);
		} else if (campgroundResponse == 1) {
			NavigationController.instance.popViewController();	
		}	
	}
	
	/**
	 * Shows all campgrounds at selected park.
	 */
	private void showCampgrounds() {
		List<Campground> listOfCampgrounds = JDBCCampgroundDAO.instance.getAllCampgroundsForPark(selectedPark);
		CampgroundTableView tableView = new CampgroundTableView(listOfCampgrounds, selectedPark.getName() + " National Park Campgrounds");
		tableView.displayTableView();	
	}
	
	/**
	 * Shows the menu for the campgrounds available and gets response.
	 * 
	 * @return the user's response
	 */
	private Integer getCampgroundsMenuResponse() {
		List<String> campgroundsMenuItems = new LinkedList<>();
		campgroundsMenuItems.add("Search for Available Reservation");
		campgroundsMenuItems.add("Return to Previous Screen");
		
		Integer campgroundsResponse = (Integer) new ChoiceMenuView(campgroundsMenuItems, "Select a Command", false).getResponseFromMenu();
		
		return campgroundsResponse;
	}

}
