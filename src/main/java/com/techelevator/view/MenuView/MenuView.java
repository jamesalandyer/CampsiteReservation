package com.techelevator.view.MenuView;

import java.util.Scanner;

import com.techelevator.AppDelegate;
import com.techelevator.view.View;

public abstract class MenuView extends View {
	
	protected Scanner in = AppDelegate.in;
	
	/**
	 * Gets the response from the user using the menu.
	 * 
	 * @return the object of the response from the user
	 */
	public abstract Object getResponseFromMenu();
	
	/**
	 * Displays a menu for the user to interact with.
	 * 
	 * @return the object of the final result of the menu
	 */
	protected abstract void displayMenu();
	
	/**
	 * Gets the input from the user.
	 * 
	 * @return the object of the entered input from the user
	 */
	protected String getInput() {
		return in.nextLine();
	}
	
}
