package com.techelevator.view.MenuView.InputMenuView;

import com.techelevator.view.SimpleView;

public final class NumberInputMenuView extends InputMenuView {

	/**
	 * Creates a menu that allows a number input from the user.
	 * 
	 * @param menuPrompt the prompt to display
	 */
	public NumberInputMenuView(String menuPrompt) {
		super(menuPrompt);
	}

	@Override
	public Object getResponseFromMenu() {
		while(true) {
			displayMenu();
			String input = getInput();
			SimpleView invalidNumberError = new SimpleView("Not a valid number. Please try again!");
			try {
				Integer enteredNumber = Integer.parseInt(input);
				
				if (enteredNumber < 0) {
					invalidNumberError.displayView();
					continue;
				} else if (enteredNumber == 0) {
					return enteredNumber;
				}
				
				return enteredNumber;
			} catch(Exception e) {
				invalidNumberError.displayView();
				continue;
			}
		}
	}
	
}
