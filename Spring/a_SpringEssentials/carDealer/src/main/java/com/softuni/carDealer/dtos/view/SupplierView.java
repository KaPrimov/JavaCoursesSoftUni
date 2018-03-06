package com.softuni.carDealer.dtos.view;

public class SupplierView {
	 private Long id;
    private String name;
    private Boolean isImporter;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Boolean getIsImporter() {
		return isImporter;
	}
	public void setIsImporter(Boolean isImporter) {
		this.isImporter = isImporter;
	}
    
    
}
