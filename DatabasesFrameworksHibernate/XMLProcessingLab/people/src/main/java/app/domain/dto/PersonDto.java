package app.domain.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.validation.Valid;
import javax.xml.bind.annotation.*;
import java.util.HashSet;
import java.util.Set;

@XmlRootElement(name = "person")
@XmlAccessorType(XmlAccessType.FIELD)
public class PersonDto {

    @Expose
    private long id;

    @XmlElement(name = "first_name")
    @Expose
    private String firstName;

    @XmlElement(name = "last_name")
    @Expose
    private String lastName;

    @XmlElement
    @Expose
    @SerializedName(value = "addressImportDto")
    @Valid
    private AddressDto address;

    @XmlElementWrapper(name = "phone_numbers")
    @XmlElement(name = "phone_number")
    @Expose
    @SerializedName(value = "phoneJsonDtos")
    @Valid
    private Set<PhoneDto> phoneNumbers;

    public PersonDto() {
        this.setPhoneNumbers(new HashSet<>());
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public AddressDto getAddress() {
        return this.address;
    }

    public void setAddress(AddressDto address) {
        this.address = address;
    }

    public Set<PhoneDto> getPhoneNumbers() {
        return this.phoneNumbers;
    }

    public void setPhoneNumbers(Set<PhoneDto> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
