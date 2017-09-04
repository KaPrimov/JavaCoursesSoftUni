package weddingplanner.weddingplanner.dto.view.json;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.math.BigInteger;

public class OrderedAgencyDto {

    @Expose
    private String name;
    @Expose
    @SerializedName("count")
    private BigInteger employeesCount;
    @Expose
    private String town;

    public OrderedAgencyDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigInteger getEmployeesCount() {
        return employeesCount;
    }

    public void setEmployeesCount(BigInteger employeesCount) {
        this.employeesCount = employeesCount;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }
}
