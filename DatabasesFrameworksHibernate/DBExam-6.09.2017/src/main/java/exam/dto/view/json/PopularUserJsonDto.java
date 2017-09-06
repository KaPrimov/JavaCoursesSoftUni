package exam.dto.view.json;

import com.google.gson.annotations.Expose;

public class PopularUserJsonDto {


    private Long id;
    @Expose
    private String username;
    @Expose
    private Integer followers;

    public PopularUserJsonDto() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getFollowers() {
        return followers;
    }

    public void setFollowers(Integer followers) {
        this.followers = followers;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
