package exam.dto.binding.json;

import com.google.gson.annotations.Expose;

public class FollowerDataImportJsonDto {

    @Expose
    private String user;
    @Expose
    private String follower;

    public FollowerDataImportJsonDto() {
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getFollower() {
        return follower;
    }

    public void setFollower(String follower) {
        this.follower = follower;
    }
}
