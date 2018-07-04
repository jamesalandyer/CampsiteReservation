package com.techelevator.controller.ViewController;

import java.util.LinkedList;
import java.util.List;

import com.techelevator.controller.NavigationController;
import com.techelevator.model.Park;
import com.techelevator.view.DetailView.ParkDetailView;
import com.techelevator.view.MenuView.ChoiceMenuView.ChoiceMenuView;

public final class ParkDetailViewController extends ViewController {

	private Park selectedPark = null;

	@Override
	public void loadView(Object data) {
	
		if (selectedPark == null) {
			selectedPark = (Park) data;
		}
		
		showParkDetail();
		Integer detailResponse = getDetailMenuResponse();
		
		if (detailResponse == 0) {
			NavigationController.instance.pushViewController(new CampgroundsViewController(), selectedPark);
		} else if (detailResponse == 1) {
			NavigationController.instance.pushViewController(new ReservationSearchViewController(), selectedPark);
		} else if (detailResponse == 2) {
			NavigationController.instance.popViewController();
		}
	}
	
	/**
	 * Shows the details of the selected park.
	 */
	private void showParkDetail() {
		ParkDetailView selectedParkDetailView = new ParkDetailView(selectedPark);
		selectedParkDetailView.displayDetailView();
	}
	
	/**
	 * Shows the detail menu and gets a response.
	 * 
	 * @return the user's response
	 */
	private Integer getDetailMenuResponse() {
		List<String> detailMenuItems = new LinkedList<>();
		detailMenuItems.add("View Campgrounds");
		detailMenuItems.add("Search for Reservation");
		detailMenuItems.add("Return to Previous Screen");
		
		Integer detailResponse = (Integer) new ChoiceMenuView(detailMenuItems, "Select a Command", false).getResponseFromMenu();
		
		return detailResponse;
	}

}
