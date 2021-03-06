package com.softuni.carDealer.dtos.binding.relations;


public class SupplierDto {
    private Long id;
    private String name;
    private Boolean isImporter;

    public SupplierDto() {
    }

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
