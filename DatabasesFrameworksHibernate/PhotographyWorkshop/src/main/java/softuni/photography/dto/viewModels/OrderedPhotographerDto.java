package softuni.photography.dto.viewModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderedPhotographerDto {

    @Expose
    @SerializedName("FistName")
    private String firstName;
    @Expose
    @SerializedName("LastName")
    private String lastName;
    @Expose
    @SerializedName("Phone")
    private String phone;

    public OrderedPhotographerDto() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
