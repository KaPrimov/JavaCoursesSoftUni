package app.domain.dto;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.*;
import java.io.Serializable;

@XmlAccessorType(XmlAccessType.FIELD)
public class PhoneDto implements Serializable {

    @XmlElement
    @Expose
    @NotNull
    private String number;

    public String getNumber() {
        return this.number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

}
