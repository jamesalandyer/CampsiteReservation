package com.techelevator.controller;

import java.util.Stack;

import com.techelevator.controller.ViewController.ViewController;

public final class NavigationController {
	
	public final static NavigationController instance = new NavigationController();
	
	private Stack<ViewController> history = new Stack<>();
	
	/**
	 * Controls the navigation of the application.
	 */
	private NavigationController() {}
	
	/**
	 * Navigates to the previous view.
	 */
	public void popViewController() {
		if (history.size() > 1) {
			history.pop();
			history.peek().loadView(null);
		}
	}
	
	/**
	 * Navigates to the next view.
	 * 
	 * @param viewController the view to add
	 * @param sender data from the previous view
	 * @return the current view
	 */
	public ViewController pushViewController(ViewController viewController, Object sender) {
		history.push(viewController);
		viewController.loadView(sender);
		
		return viewController;
	}
	
	/**
	 * Navigates to the first view.
	 */
	public void popToFirst() {
		while(history.size() > 1) {
			history.pop();
		}
		history.peek().loadView(null);
	}
	
}
