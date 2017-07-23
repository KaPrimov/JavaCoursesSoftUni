package c_Hotel.entities;

import javax.persistence.*;

@Entity(name = "rooms")
public class Room {
    private Long roomNumber;
    private RoomType roomType;
    private BedType bedType;
    private Double rate;
    private RoomStatus roomStatus;
    private String notes;

    public Room() {
    }

    @Id
    public Long getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Long roomNumber) {
        this.roomNumber = roomNumber;
    }

    @ManyToOne
    @JoinColumn(referencedColumnName = "type")
    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    @ManyToOne
    @JoinColumn(referencedColumnName = "type")
    public BedType getBedType() {
        return bedType;
    }

    public void setBedType(BedType bedType) {
        this.bedType = bedType;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    @ManyToOne
    @JoinColumn(referencedColumnName = "roomStatus")
    public RoomStatus getRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(RoomStatus roomStatus) {
        this.roomStatus = roomStatus;
    }

    @Column(name = "TEXT")
    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
