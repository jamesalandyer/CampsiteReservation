package com.techelevator.view.DetailView;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import com.techelevator.model.Park;

public final class ParkDetailView extends DetailView {
	
	private Park park;
	
	/**
	 * Creates a detail view for a park.
	 * 
	 * @param park the park to create the view for
	 */
	public ParkDetailView(Park park) {
		this.park = park;
	}
	
	@Override
	public void displayDetailView() {
		out.println();
		out.println(park.getName() + " National Park");
		out.println();
		out.printf("%-20s %-15s\n","Location:", park.getLocation());
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		out.printf("%-20s %-15s\n","Established:", df.format(Date.valueOf(park.getEstablishDate())));
		out.printf("%-20s %-15s\n","Area:", String.format("%,d", park.getArea()) + " sq km");
		out.printf("%-20s %-15s\n","Annual Visitors:", String.format("%,d", park.getVisitors()));
		out.println();
		
		displayFormattedParagraph(park.getDescription(), 50);
		
		out.flush();
	}
	
	/**
	 * Formats a paragraph to wrap at a certain length and displays it.
	 * 
	 * @param paragraph the paragraph to format
	 * @param lineLength the max line length
	 */
	private void displayFormattedParagraph(String paragraph, int lineLength) {
		int startIndex = 0;
		int endIndex = paragraph.length();
		
		while(startIndex < endIndex) {
			out.println(paragraph.substring(startIndex, (startIndex + lineLength > endIndex) ? endIndex : startIndex + lineLength));
			startIndex = startIndex + lineLength;
		}
	}
}
