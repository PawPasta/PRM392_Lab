package com.pawpasta.se182138_lab5;

public class User {
    private final String username;
    private final String fullname;
    private final String email;

    public User(String username, String fullname, String email) {
        this.username = username;
        this.fullname = fullname;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getFullname() {
        return fullname;
    }

    public String getEmail() {
        return email;
    }
}
