package com.softuni.residentEvil.models.view;

import java.util.Set;

public class ViewUser {
    private String username;
    private Set<String> authorities;

    public ViewUser() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<String> authorities) {
        this.authorities = authorities;
    }
}
