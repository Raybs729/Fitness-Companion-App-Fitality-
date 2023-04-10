package com.techelevator.model;

import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

public class Account {
    private int userId;
    @NotEmpty
    private String email;
    @NotEmpty
    private int phone;
    @NotEmpty
    private int height;
    @NotEmpty
    private int age;
    private String photo;
    private String goals;

    public Account() {}

    public Account(int userId, String email, int phone, int height, int age, String photo, String goals) {
        this.userId = userId;
        this.email = email;
        this.phone = phone;
        this.height = height;
        this.age = age;
        this.photo = photo;
        this.goals = goals;

    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getGoals() {
        return goals;
    }

    public void setGoals(String goals) {
        this.goals = goals;
    }
}
