package exam.dto.binding.json;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserImportFromJsonDto {

    @Expose
    private String username;
    @Expose
    private String password;
    @Expose
    @SerializedName("profile_picture")
    private String path;

    public UserImportFromJsonDto() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
