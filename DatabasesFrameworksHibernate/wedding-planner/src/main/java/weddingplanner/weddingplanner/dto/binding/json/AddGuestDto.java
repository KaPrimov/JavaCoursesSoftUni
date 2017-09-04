package weddingplanner.weddingplanner.dto.binding.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddGuestDto {

    @Expose
    @SerializedName("Name")
    private String name;
    @Expose
    @SerializedName("RSVP")
    private boolean isAttending;
    @Expose
    @SerializedName("Family")
    private String family;

    public AddGuestDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAttending() {
        return isAttending;
    }

    public void setAttending(boolean attending) {
        isAttending = attending;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }
}
