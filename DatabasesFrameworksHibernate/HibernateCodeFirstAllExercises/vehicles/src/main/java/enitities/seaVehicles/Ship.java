package enitities.seaVehicles;


import javax.persistence.*;

@Entity
@Table(name = "ships")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Ship {

    private long id;

    private String nationality;

    private String captainName;

    private byte crewSize;

    public Ship() {
    }

    public Ship(String nationality, String captainName, byte crewSize) {
        this.nationality = nationality;
        this.captainName = captainName;
        this.crewSize = crewSize;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    @Column(name = "captain_name")
    public String getCaptainName() {
        return captainName;
    }

    public void setCaptainName(String captainName) {
        this.captainName = captainName;
    }

    @Column(name = "crew_size")
    public byte getCrewSize() {
        return crewSize;
    }

    public void setCrewSize(byte crewSize) {
        this.crewSize = crewSize;
    }
}
