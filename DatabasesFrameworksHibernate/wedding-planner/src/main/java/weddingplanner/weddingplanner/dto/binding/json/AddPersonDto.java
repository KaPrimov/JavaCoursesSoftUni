package weddingplanner.weddingplanner.dto.binding.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import weddingplanner.weddingplanner.entities.enums.Gender;

import java.util.Date;

public class AddPersonDto {

    @Expose
    private String firstName;
    @Expose
    private String lastName;
    @Expose
    @SerializedName("middleInitial")
    private String middleNameInitial;
    @Expose
    private Gender gender;
    @Expose
    @SerializedName("birthday")
    private Date birthdate;
    @Expose
    private String phone;
    @Expose
    private String email;

    public AddPersonDto() {
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

    public String getMiddleNameInitial() {
        return middleNameInitial;
    }

    public void setMiddleNameInitial(String middleNameInitial) {
        this.middleNameInitial = middleNameInitial;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthdate;
    }

    public void setBirthday(Date birthday) {
        this.birthdate = birthday;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
