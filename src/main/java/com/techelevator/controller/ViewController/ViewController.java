package com.techelevator.controller.ViewController;

public abstract class ViewController {
	
	/**
	 * Controls the logic of the screens.
	 */
	public ViewController() {}
	
	/**
	 * Loads the view.
	 * 
	 * @param data information that can be passed in to load the view
	 */
	public abstract void loadView(Object data);
	
}
