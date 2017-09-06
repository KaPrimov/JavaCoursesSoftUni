package exam.dto.view.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UncommentedPostDto {

    @Expose
    private Long id;
    @Expose
    @SerializedName("picture")
    private String picturePath;
    @Expose
    @SerializedName("user")
    private String userUsername;

    public UncommentedPostDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    public String getUserUsername() {
        return userUsername;
    }

    public void setUserUsername(String userUsername) {
        this.userUsername = userUsername;
    }
}
