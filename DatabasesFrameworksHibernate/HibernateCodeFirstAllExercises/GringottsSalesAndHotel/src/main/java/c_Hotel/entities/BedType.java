package c_Hotel.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "bed_types")
public class BedType {
    private String type;
    private String notes;

    public BedType() {
    }

    @Id
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Column(columnDefinition = "TEXT")
    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
