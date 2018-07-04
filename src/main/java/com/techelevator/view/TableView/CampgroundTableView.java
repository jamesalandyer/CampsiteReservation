package com.techelevator.view.TableView;

import java.text.DateFormatSymbols;
import java.text.NumberFormat;
import java.util.List;

import com.techelevator.model.Campground;

public final class CampgroundTableView extends TableView {

	private List<Campground> campgrounds;
	private String parkName;
	
	/**
	 * Creates a tableview for a campground.
	 * 
	 * @param campgrounds the campgrounds to display in the table
	 * @param parkName the park name of where the campgrounds are
	 */
	public CampgroundTableView(List<Campground> campgrounds, String parkName) {
		this.campgrounds = campgrounds;
		this.parkName = parkName;
	}

	@Override
	public void displayTableView() {
		out.println();
		out.println(parkName);
		out.println();
		out.printf("%-8s %-35s %-15s %-15s %-15s\n","", "Name", "Open", "Close", "Daily fee");
		NumberFormat formatter = NumberFormat.getCurrencyInstance();
		for (int i = 1; i <= campgrounds.size(); i++) {
			Campground campground = campgrounds.get(i-1);
			Integer openFromMonth = Integer.parseInt(campground.getOpenFromMM());
			Integer openToMonth = Integer.parseInt(campground.getOpenToMM());
			out.printf("%-8s %-35s %-15s %-15s %-15s\n","#"+i, campground.getName(), getMonth(openFromMonth), getMonth(openToMonth), formatter.format(campground.getDailyFee()));
		}
		out.flush();
	}
	
	/**
	 * Gets the month name from a number.
	 * 
	 * @param month the month to convert
	 * @return the month name
	 */
	private String getMonth(int month) {
	    return new DateFormatSymbols().getMonths()[month-1];
	}

}
