package com.softuni.residentEvil.models.binding;

import java.util.Date;
import java.util.Set;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.softuni.residentEvil.annotations.PresentOrPass;

public class FullVirusDTO {
	
	@NotNull
	@Size(min = 3, max = 10, message = "Invalid name")
	private String name;

	@NotNull
	@Size(min = 5, max = 100, message = "Invalid description")
	private String description;	

	@Size(max = 50, message = "Invalid side effect")
	private String sideEffect;

	@Pattern(regexp = "^[c|C]orp$", message = "Invalid creator")
	private String creator;
	
	private String isDeadly;
	
	private String isCurable;
	
	@NotNull(message = "Mutation can not be null")
	private String mutation;
	
	@Min(value = 0, message = "Invalid value")
	@Max(value = 100, message = "Invalid value")
	private Byte turnoverRate; 
	
	@Min(value = 1, message = "Invalid value")
	@Max(value = 12, message = "Invalid value")
	private Byte hoursUntilTurn;
	
	@NotNull
	private String magnitude;
	
	@PresentOrPass
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date releasedOn;
	
	@Size(min = 1, message = "Select capital")
	private Set<String> capitals;

	public FullVirusDTO() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSideEffect() {
		return sideEffect;
	}

	public void setSideEffect(String sideEffect) {
		this.sideEffect = sideEffect;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getIsDeadly() {
		return isDeadly;
	}

	public void setIsDeadly(String isDeadly) {
		this.isDeadly = isDeadly;
	}

	public String getIsCurable() {
		return isCurable;
	}

	public void setIsCurable(String isCurable) {
		this.isCurable = isCurable;
	}

	public String getMutation() {
		return mutation;
	}

	public void setMutation(String mutation) {
		this.mutation = mutation;
	}

	public Byte getTurnoverRate() {
		return turnoverRate;
	}

	public void setTurnoverRate(Byte turnoverRate) {
		this.turnoverRate = turnoverRate;
	}

	public Byte getHoursUntilTurn() {
		return hoursUntilTurn;
	}

	public void setHoursUntilTurn(Byte hoursUntilTurn) {
		this.hoursUntilTurn = hoursUntilTurn;
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

	public Set<String> getCapitals() {
		return capitals;
	}

	public void setCapitals(Set<String> capitals) {
		this.capitals = capitals;
	}
}
