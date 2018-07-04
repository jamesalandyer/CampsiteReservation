package com.techelevator.view.MenuView.InputMenuView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import com.techelevator.view.SimpleView;

public final class DateInputMenuView extends InputMenuView {

	/**
	 * Creates a menu that allows a date input from the user.
	 * 
	 * @param menuPrompt the prompt to display
	 */
	public DateInputMenuView(String menuPrompt) {
		super(menuPrompt);
	}
	
	@Override
	public Object getResponseFromMenu() {
		Object response = null;
		
		while(true) {
			displayMenu();
			String input = getInput();
			try {
				response = convertToLocalDate(input);
				break;
			} catch(Exception e) {
				SimpleView invalidDateError = new SimpleView("Not a valid date. Please try again!");
				invalidDateError.displayView();
				continue;
			}
		}
		
		return response;
	}
	
	/**
	 * Convert a date string into a local date.
	 * 
	 * @param date the date to convert in MM/dd/yyyy format
	 * @return the converted local date
	 * @throws ParseException if the string is not formatted correctly
	 */
	private LocalDate convertToLocalDate(String date) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		Date formattedDate = formatter.parse(date);
		LocalDate convertedDate = formattedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		
		return convertedDate;
	}

}
