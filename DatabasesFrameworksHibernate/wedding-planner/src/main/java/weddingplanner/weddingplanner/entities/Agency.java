package weddingplanner.weddingplanner.entities;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(name = "agencies")
public class Agency {

    private Long id;
    private String name;
    private BigInteger employeesCount;
    private String town;

    public Agency() {
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

    @Column(name = "employees_count")
    public BigInteger getEmployeesCount() {
        return employeesCount;
    }

    public void setEmployeesCount(BigInteger employeesCount) {
        this.employeesCount = employeesCount;
    }

    @Basic
    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }
}
