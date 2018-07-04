package com.techelevator.model;

public final class Site {
	
	private long id;
	private long campgroundId;
	private int siteNumber;
	private int maxOccupancy;
	private boolean accessible;
	private int maxRVLength;
	private boolean utilities;
	
	public Site( long id, long campgroundId, int siteNumber, int maxOccupancy, boolean accessible, int maxRVLength, boolean utilities) {
		this.id = id;
		this.campgroundId = campgroundId;
		this.siteNumber = siteNumber;
		this.maxOccupancy = maxOccupancy;
		this.accessible = accessible;
		this.maxRVLength = maxRVLength;
		this.utilities = utilities;
	}
	
	public long getId() {
		return id;
	}
	
	public long getCampgroundId() {
		return campgroundId;
	}
	
	public int getSiteNumber() {
		return siteNumber;
	}
	
	public int getMaxOccupancy() {
		return maxOccupancy;
	}
	
	public boolean isAccessible() {
		return accessible;
	}
	
	public int getMaxRVLength() {
		return maxRVLength;
	}
	
	public boolean isUtilities() {
		return utilities;
	}
	
}
