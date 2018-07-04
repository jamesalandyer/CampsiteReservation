package com.techelevator.view.MenuView.ChoiceMenuView;

import java.util.List;

import com.techelevator.view.SimpleView;
import com.techelevator.view.MenuView.MenuView;

public final class ChoiceMenuView extends MenuView {

	private List<String> menuItems;
	private String header;
	private boolean exitOption;
	
	/**
	 * Creates a menu that allows multiple choice.
	 * 
	 * @param menuItems the menu items to create the menu with
	 * @param header the header for the menu
	 * @param exitOption adds the option to quit the program to the menu
	 */
	public ChoiceMenuView(List<String> menuItems, String header, boolean exitOption) {
		this.menuItems = menuItems;
		this.header = header;
		this.exitOption = exitOption;
	}
	
	@Override
	public Object getResponseFromMenu() {
		while (true) {
			displayMenu();
			String userInput = getInput();

			SimpleView optionError = new SimpleView("Invalid Option. Please select a valid option!");
			try {
				if (userInput.toUpperCase().equals("Q")) {
					System.exit(0);
				}
				
				int selectedOption = Integer.valueOf(userInput);
				if (selectedOption <= menuItems.size() && selectedOption > 0) {
					return selectedOption - 1;
				} else {
					optionError.displayView();
				}
					
			} catch(NumberFormatException e) {
				optionError.displayView();
			}
		}
	}
	
	@Override
	protected void displayMenu() {
		out.println();
		out.println(header);
		
		for (int i = 1; i <= menuItems.size(); i++) {
			out.printf("%-2s %-4s %-15s\n", "", i + ")", menuItems.get(i - 1));
		}
		
		if (exitOption) {
			out.printf("%-2s %-4s %-15s\n", "", "Q)", "quit");
		}

		out.println();
		out.print("Select an option: ");
		out.flush();
	}

}
