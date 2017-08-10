package softuni.photography.dto.viewModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LandscapePhotographersDto {

    @Expose
    @SerializedName("FirstName")
    private String firstName;
    @Expose
    @SerializedName("LastName")
    private String lastName;
    @Expose
    @SerializedName("CameraMake")
    private String cameraMake;
    @Expose
    @SerializedName("LensesCount")
    private Integer lensesCount;

    public LandscapePhotographersDto() {
    }

    public LandscapePhotographersDto(String firstName, String lastName, String cameraMake, Integer lensesCount) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.cameraMake = cameraMake;
        this.lensesCount = lensesCount;
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

    public String getCameraMake() {
        return cameraMake;
    }

    public void setCameraMake(String cameraMake) {
        this.cameraMake = cameraMake;
    }

    public Integer getLensesCount() {
        return lensesCount;
    }

    public void setLensesCount(Integer lensesCount) {
        this.lensesCount = lensesCount;
    }
}
