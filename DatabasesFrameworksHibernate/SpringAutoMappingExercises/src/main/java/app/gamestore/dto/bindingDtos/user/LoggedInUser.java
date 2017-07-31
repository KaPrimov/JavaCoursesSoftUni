package app.gamestore.dto.bindingDtos.user;

import app.gamestore.entities.enums.Role;

public class LoggedInUser {
    private Long id;
    private Role role;
    private String fullName;

    public LoggedInUser() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}

