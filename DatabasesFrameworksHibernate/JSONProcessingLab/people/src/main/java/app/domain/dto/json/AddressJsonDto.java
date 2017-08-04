package app.domain.dto.json;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

public class AddressJsonDto implements Serializable {

    @Expose
    private String country;

    @Expose
    private String city;

    @Expose
    private String street;

    public AddressJsonDto() {
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return this.street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
}
