package weddingplanner.weddingplanner.dto.binding.json;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.Set;

public class AddWeddingDto {
    @Expose
    @SerializedName("Bride")
    private String bride;
    @Expose
    @SerializedName("Bridegroom")
    private String bridegroom;
    @Expose
    @SerializedName("Date")
    private Date date;
    @Expose
    @SerializedName("Agency")
    private String agency;
    @Expose
    @SerializedName("Guests")
    private Set<AddGuestDto> guests;

    public AddWeddingDto() {
    }

    public String getBride() {
        return bride;
    }

    public void setBride(String bride) {
        this.bride = bride;
    }

    public String getBridegroom() {
        return bridegroom;
    }

    public void setBridegroom(String bridegroom) {
        this.bridegroom = bridegroom;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public Set<AddGuestDto> getGuests() {
        return guests;
    }

    public void setGuests(Set<AddGuestDto> guests) {
        this.guests = guests;
    }
}
