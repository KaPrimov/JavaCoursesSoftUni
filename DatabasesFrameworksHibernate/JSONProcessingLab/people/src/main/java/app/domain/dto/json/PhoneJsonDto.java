package app.domain.dto.json;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

public class PhoneJsonDto implements Serializable {

    @Expose
    private String number;

    @Expose
    private long personId;

    public String getNumber() {
        return this.number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public long getPersonId() {
        return personId;
    }

    public void setPersonId(long personId) {
        this.personId = personId;
    }
}
