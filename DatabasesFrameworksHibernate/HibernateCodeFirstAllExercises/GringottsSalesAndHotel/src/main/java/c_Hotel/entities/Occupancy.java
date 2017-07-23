package c_Hotel.entities;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "occupancies")
public class Occupancy {
    private Long id;
    private Date dateOccupied;
    private Customer accountNumber;
    private Room roomNumber;
    private Double rateApplied;
    private Double phoneCharge;
    private String notes;

    public Occupancy() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateOccupied() {
        return dateOccupied;
    }

    public void setDateOccupied(Date dateOccupied) {
        this.dateOccupied = dateOccupied;
    }

    @ManyToOne
    @JoinColumn(referencedColumnName = "accountNumber")
    public Customer getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Customer accountNumber) {
        this.accountNumber = accountNumber;
    }

    @ManyToOne
    @JoinColumn(referencedColumnName = "roomNumber")
    public Room getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Room roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Double getRateApplied() {
        return rateApplied;
    }

    public void setRateApplied(Double rateApplied) {
        this.rateApplied = rateApplied;
    }

    public Double getPhoneCharge() {
        return phoneCharge;
    }

    public void setPhoneCharge(Double phoneCharge) {
        this.phoneCharge = phoneCharge;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
