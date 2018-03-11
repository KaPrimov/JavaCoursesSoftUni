package com.softuni.residentEvil.models.view;

import java.sql.Date;

public class ListViewVirusDTO {
	private String id;
	private String name;
	private String magnitude;
	private Date releasedOn;
	
	public ListViewVirusDTO() {}
 
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMagnitude() {
		return magnitude;
	}

	public void setMagnitude(String magnitude) {
		this.magnitude = magnitude;
	}

	public Date getReleasedOn() {
		return releasedOn;
	}

	public void setReleasedOn(Date releasedOn) {
		this.releasedOn = releasedOn;
	}
}
