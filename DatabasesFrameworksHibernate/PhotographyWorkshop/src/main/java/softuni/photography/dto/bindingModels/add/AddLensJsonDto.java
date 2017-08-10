package softuni.photography.dto.bindingModels.add;

import com.google.gson.annotations.Expose;

import java.math.BigDecimal;

public class AddLensJsonDto {
    @Expose
    private String make;
    @Expose
    private Integer focalLength;
    @Expose
    private BigDecimal maxAperture;
    @Expose
    private String compatibleWith;

    public AddLensJsonDto() {
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public Integer getFocalLength() {
        return focalLength;
    }

    public void setFocalLength(Integer focalLength) {
        this.focalLength = focalLength;
    }

    public BigDecimal getMaxAperture() {
        return maxAperture;
    }

    public void setMaxAperture(BigDecimal maxAperture) {
        this.maxAperture = maxAperture;
    }

    public String getCompatibleWith() {
        return compatibleWith;
    }

    public void setCompatibleWith(String compatibleWith) {
        this.compatibleWith = compatibleWith;
    }

}
