package com.techelevator.controller.ViewController;

import java.util.LinkedList;
import java.util.List;

import com.techelevator.controller.NavigationController;
import com.techelevator.model.Park;
import com.techelevator.model.JDBC.JDBCParkDAO;
import com.techelevator.view.SimpleView;
import com.techelevator.view.MenuView.ChoiceMenuView.ChoiceMenuView;

public final class ParksViewController extends ViewController {
	
	@Override
	public void loadView(Object data) {
		showParksHeader();
		Park selectedPark = getParkMenuResponse();
		NavigationController.instance.pushViewController(new ParkDetailViewController(), selectedPark);
	}
	
	/**
	 * Shows the header of the parks.
	 */
	private void showParksHeader() {
		SimpleView parksHeader = new SimpleView("NATIONAL PARK CAMPSITE RESERVATION");
		parksHeader.displayView();
	}
	
	/**
	 * Shows the park menu and gets a response.
	 * 
	 * @return the selected park
	 */
	private Park getParkMenuResponse() {
		List<Park> allParks = JDBCParkDAO.instance.getAllParks();
		
		List<String> parkMenuItems = new LinkedList<String>();
		for (Park park : allParks) {
			parkMenuItems.add(park.getName());
		}
		
		Integer parksResponse = (Integer) new ChoiceMenuView(parkMenuItems, "Select a Park for Further Details", true).getResponseFromMenu();
		Park selectedPark = allParks.get(parksResponse);
		
		return selectedPark;
	}

}
