package com.softuni.carDealer.entities;

import javax.persistence.*;

import org.hibernate.annotations.Cascade;

import java.util.Set;

@Entity
@Table(name = "suppliers")
public class Supplier {

    private Long id;
    private String name;
    private Boolean isImporter;
    private Set<Part> parts;

    public Supplier() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    @Column(name = "is_importer")
    public Boolean getIsImporter() {
		return isImporter;
	}

	public void setIsImporter(Boolean isImporter) {
		this.isImporter = isImporter;
	}

	@OneToMany(mappedBy = "supplier", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
	@Cascade(org.hibernate.annotations.CascadeType.DELETE)
    public Set<Part> getParts() {
        return parts;
    }

    public void setParts(Set<Part> parts) {
        this.parts = parts;
    }
}
