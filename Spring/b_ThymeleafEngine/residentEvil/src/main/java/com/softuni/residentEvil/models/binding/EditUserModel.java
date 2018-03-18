package com.softuni.residentEvil.models.binding;

import javax.validation.constraints.NotNull;
import java.util.Set;

public class EditUserModel {

    private Long id;
    @NotNull
    private String username;
    @NotNull
    private String email;
    private Set<String> addRoles;
    private Set<String> removeRoles;

    public EditUserModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<String> getAddRoles() {
        return addRoles;
    }

    public void setAddRoles(Set<String> addRoles) {
        this.addRoles = addRoles;
    }

    public Set<String> getRemoveRoles() {
        return removeRoles;
    }

    public void setRemoveRoles(Set<String> removeRoles) {
        this.removeRoles = removeRoles;
    }
}
