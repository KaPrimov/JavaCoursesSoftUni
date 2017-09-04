package weddingplanner.weddingplanner.dto.view.json;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WeddingGuestsDto {
    @Expose
    @SerializedName("bride")
    private String brideFullName;
    @Expose
    @SerializedName("bridegroom")
    private String bridegroomFullName;
    @Expose
    private AgencyDto agency;
    @Expose
    private Long invitedGuests;
    @Expose
    private Long brideGuests;
    @Expose
    private Long bridegroomGuests;
    @Expose
    private Long attendingGuests;
    @Expose
    private String[] guests;

    public WeddingGuestsDto() {
    }

    public String getBrideFullName() {
        return brideFullName;
    }

    public void setBrideFullName(String brideFullName) {
        this.brideFullName = brideFullName;
    }

    public String getBridegroomFullName() {
        return bridegroomFullName;
    }

    public void setBridegroomFullName(String bridegroomFullName) {
        this.bridegroomFullName = bridegroomFullName;
    }

    public AgencyDto getAgency() {
        return agency;
    }

    public void setAgency(AgencyDto agency) {
        this.agency = agency;
    }

    public Long getInvitedGuests() {
        return invitedGuests;
    }

    public void setInvitedGuests(Long invitedGuests) {
        this.invitedGuests = invitedGuests;
    }

    public Long getBrideGuests() {
        return brideGuests;
    }

    public void setBrideGuests(Long brideGuests) {
        this.brideGuests = brideGuests;
    }

    public Long getAttendingGuests() {
        return attendingGuests;
    }

    public void setAttendingGuests(Long attendingGuests) {
        this.attendingGuests = attendingGuests;
    }

    public String[] getGuests() {
        return guests;
    }

    public void setGuests(String[] guests) {
        this.guests = guests;
    }

    public Long getBridegroomGuests() {
        return bridegroomGuests;
    }

    public void setBridegroomGuests(Long bridegroomGuests) {
        this.bridegroomGuests = bridegroomGuests;
    }
}
