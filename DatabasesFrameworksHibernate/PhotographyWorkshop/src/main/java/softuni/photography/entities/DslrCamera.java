package softuni.photography.entities;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
@DiscriminatorValue(value = "DSLR")
public class DslrCamera extends Camera {

    private Integer maxShutterSpeed;

    public DslrCamera() {
    }

    @Column(name = "max_shutter_speed")
    public Integer getMaxShutterSpeed() {
        return maxShutterSpeed;
    }

    public void setMaxShutterSpeed(Integer maxShutterSpeed) {
        this.maxShutterSpeed = maxShutterSpeed;
    }

    @Override
    @Transient
    public String getType() {
        return "DSLR";
    }
}
