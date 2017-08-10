package softuni.photography.entities;

import softuni.photography.validation.DecimalDigitsValidation;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "lenses")
public class Lens {

    private Long id;
    private String make;
    private Integer focalLength;
    @DecimalDigitsValidation(fraction = 1)
    @Digits(integer=5, fraction=1)
    private BigDecimal maxAperture;
    private String compatibleWith;
    private Set<Photographer> owners;

    public Lens() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "make")
    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    @Column(name = "focal_length")
    public Integer getFocalLength() {
        return focalLength;
    }

    public void setFocalLength(Integer focalLength) {
        this.focalLength = focalLength;
    }

    @Column(name = "max_aperture")
    public BigDecimal getMaxAperture() {
        return maxAperture;
    }

    public void setMaxAperture(BigDecimal maxAperture) {
        this.maxAperture = maxAperture;
    }

    @Column(name = "compatible_with")
    public String getCompatibleWith() {
        return compatibleWith;
    }

    public void setCompatibleWith(String compatibleWith) {
        this.compatibleWith = compatibleWith;
    }

    @ManyToMany
    @JoinTable(name = "lenses_photographers", joinColumns = @JoinColumn(name = "lens_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "photographer_id", referencedColumnName = "id"))
    public Set<Photographer> getOwners() {
        return owners;
    }

    public void setOwners(Set<Photographer> owners) {
        this.owners = owners;
    }
}
