package c_Hotel.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "room_types")
public class RoomType {
    private String type;
    private String notes;

    public RoomType() {
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
