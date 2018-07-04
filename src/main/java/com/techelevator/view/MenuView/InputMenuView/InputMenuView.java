package com.techelevator.view.MenuView.InputMenuView;

import com.techelevator.view.MenuView.MenuView;

public class InputMenuView extends MenuView {
	
	protected String menuPrompt;
	
	/**
	 * Creates a menu that allows input from the user.
	 * 
	 * @param menuPrompt the prompt to display
	 */
	public InputMenuView(String menuPrompt) {
		this.menuPrompt = menuPrompt;
	}
	
	@Override
	public Object getResponseFromMenu() {
		displayMenu();
		return getInput();
	}
	
	@Override
	protected void displayMenu() {
		out.println();
		out.print(menuPrompt);
		out.flush();
	}

}
