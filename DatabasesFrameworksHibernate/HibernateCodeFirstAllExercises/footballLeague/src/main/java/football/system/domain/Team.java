package football.system.domain;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity(name = "teams")
public class Team {
    private Long id;
    private String name;
    private String logo;
    private String initial;
    private Color primaryColor;
    private Color secondaryColor;
    private Town town;
    private BigDecimal budget;

    public Team() {
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

    @Basic
    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    @Column(length = 3)
    public String getInitial() {
        return initial;
    }

    public void setInitial(String initial) {
        this.initial = initial;
    }

    @ManyToOne
    @JoinColumn(name = "primary_color_id")
    public Color getPrimaryColor() {
        return primaryColor;
    }

    public void setPrimaryColor(Color primaryColor) {
        this.primaryColor = primaryColor;
    }

    @ManyToOne
    @JoinColumn(name = "secondary_color_id")
    public Color getSecondaryColor() {
        return secondaryColor;
    }

    public void setSecondaryColor(Color secondaryColor) {
        this.secondaryColor = secondaryColor;
    }

    @ManyToOne
    @JoinColumn(name = "town_id")
    public Town getTown() {
        return town;
    }

    public void setTown(Town town) {
        this.town = town;
    }

    @Basic
    public BigDecimal getBudget() {
        return budget;
    }

    public void setBudget(BigDecimal budget) {
        this.budget = budget;
    }
}
