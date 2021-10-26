package it.javaproject.springwebapp.model;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class User {
    private String username;
    private String password;
    private String photo;
    private String address;
    private String email;

    //ricordiamoci sempre il costruttore vuoto necessario a Spring
    public User() {
    }

    public User(String username, String password, String photo, String address, String email) {
        this.username = username;
        this.password = password;
        this.photo = photo;
        this.address = address;
        this.email = email;
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

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    //ricordatevi di fare l’override del metodo toString()

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", photo='" + photo + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
