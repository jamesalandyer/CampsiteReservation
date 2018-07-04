package com.techelevator.view;

public final class SimpleView extends View {

	private String message;
	
	/**
	 * A view that displays a message.
	 * 
	 * @param message the message to display to the screen
	 */
	public SimpleView(String message) {
		this.message = message;
	}
	
	/**
	 * Displays the view to the screen.
	 */
	public void displayView() {
		out.println();
		out.println(message);
		out.flush();
	}
	
}
