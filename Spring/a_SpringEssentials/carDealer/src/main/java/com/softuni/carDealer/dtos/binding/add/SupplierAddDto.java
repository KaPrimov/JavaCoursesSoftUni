package com.softuni.carDealer.dtos.binding.add;

import java.util.List;

public class SupplierAddDto {

    private String name;
    private Boolean isImporter = false;
    private List<Long> parts;

    public SupplierAddDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getImporter() {
        return isImporter;
    }

    public void setImporter(Boolean isImporter) {
        this.isImporter = isImporter;
    }

	public Boolean getIsImporter() {
		return isImporter;
	}

	public void setIsImporter(Boolean isImporter) {
		this.isImporter = isImporter;
	}

	public List<Long> getParts() {
		return parts;
	}

	public void setParts(List<Long> parts) {
		this.parts = parts;
	}
}
