package com.softuni.residentEvil.entities;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.softuni.residentEvil.entities.enums.MagnitudeEnum;
import com.softuni.residentEvil.entities.enums.MutationEnum;

@Entity
@Table(name = "VIRUSES")
public class Virus {
	
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(
			name = "UUID",
			strategy = "org.hibernate.id.UUIDGenerator"
	)
	private String id;
	
	@Column(nullable = false)
	private String name;

	@Column(nullable = false, columnDefinition = "text")
	private String description;
	
	@Column(nullable = false)
	private String sideEffect;

	@Column(nullable = false)
	private String creator;
	
	@Column(nullable = false)
	private Boolean isDeadly;
	
	@Column(nullable = false)
	private Boolean isCurable;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private MutationEnum mutation;
	
	@Column(nullable = false)
	private Byte turnoverRate; 
	
	@Column(nullable = false)
	private Byte hoursUntilTurn;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private MagnitudeEnum magnitude;
	
	@Column(nullable = false)
	private Date releasedOn;
	
	@ManyToMany
	@JoinTable(name = "viruses_capitals", 
		joinColumns = @JoinColumn(referencedColumnName = "id", name = "virus_id"),
		inverseJoinColumns = @JoinColumn(referencedColumnName = "id", name = "capital_id"))
	private Set<Capital> capitals;

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

	public Boolean getIsDeadly() {
		return isDeadly;
	}

	public void setIsDeadly(Boolean isDeadly) {
		this.isDeadly = isDeadly;
	}

	public Boolean getIsCurable() {
		return isCurable;
	}

	public void setIsCurable(Boolean isCurable) {
		this.isCurable = isCurable;
	}

	public MutationEnum getMutation() {
		return mutation;
	}

	public void setMutation(MutationEnum mutation) {
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

	public MagnitudeEnum getMagnitude() {
		return magnitude;
	}

	public void setMagnitude(MagnitudeEnum magnitude) {
		this.magnitude = magnitude;
	}

	public Date getReleasedOn() {
		return releasedOn;
	}

	public void setReleasedOn(Date releasedOn) {
		this.releasedOn = releasedOn;
	}

	public Set<Capital> getCapitals() {
		return capitals;
	}

	public void setCapitals(Set<Capital> capitals) {
		this.capitals = capitals;
	}
}
