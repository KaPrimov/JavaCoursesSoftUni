package com.softuni.carDealer.dtos.binding.add;

public class CustomerAddDto {
    private String name;
    private String birthDate;
    private Boolean isYoungDriver;
    private Long id;

    public CustomerAddDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public Boolean getYoungDriver() {
        return isYoungDriver;
    }

    public void setYoungDriver(Boolean youngDriver) {
        isYoungDriver = youngDriver;
    }

	public Boolean getIsYoungDriver() {
		return isYoungDriver;
	}

	public void setIsYoungDriver(Boolean isYoungDriver) {
		this.isYoungDriver = isYoungDriver;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
    
    
}
