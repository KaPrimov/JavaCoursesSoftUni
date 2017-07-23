package c_Hotel.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class RoomStatus {
    private Long roomStatus;
    private String notes;

    public RoomStatus() {
    }

    @Id
    public Long getRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(Long roomStatus) {
        this.roomStatus = roomStatus;
    }

    @Column(columnDefinition = "TEXT")
    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
