package com.techelevator.model;

import java.time.LocalDate;

public final class Park {
	
	private long id;
	private String name;
	private String location;
	private LocalDate establishDate;
	private int area;
	private int visitors;
	private String description;
	
	public Park(long id, String name, String location, LocalDate establishDate, int area, int visitors, String description) {
		this.id = id;
		this.name = name;
		this.location = location;
		this.establishDate = establishDate;
		this.area = area;
		this.visitors = visitors;
		this.description = description;
	}
	
	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getLocation() {
		return location;
	}

	public LocalDate getEstablishDate() {
		return establishDate;
	}

	public int getArea() {
		return area;
	}

	public int getVisitors() {
		return visitors;
	}

	public String getDescription() {
		return description;
	}
	
}

