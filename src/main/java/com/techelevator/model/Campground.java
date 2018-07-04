package com.techelevator.model;

import java.math.BigDecimal;

public final class Campground {
	
	private long id;
	private long parkId;
	private String name;
	private String openFromMM;
	private String openToMM;
	private BigDecimal dailyFee;
	
	public Campground (long id, long parkId, String name, String openFromMM, String openToMM, BigDecimal dailyFee) {
		this.id = id;
		this.parkId = parkId;
		this.name = name;
		this.openFromMM = openFromMM;
		this.openToMM = openToMM;
		this.dailyFee = dailyFee;
	}
	
	public long getId() {
		return id;
	}
	
	public long getParkId() {
		return parkId;
	}
	
	public String getName() {
		return name;
	}
	
	public String getOpenFromMM() {
		return openFromMM;
	}
	
	public String getOpenToMM() {
		return openToMM;
	}
	
	public BigDecimal getDailyFee() {
		return dailyFee;
	}
	
}

