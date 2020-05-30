package com.example.TriviaGame.DataBase;

import java.io.Serializable;

public class UserDetails implements Serializable  {

    private long id;
   private String name;
    private String email;
    private String phone;
    private String birthday;
    private String password;
    private String gender;
   private String score;

    public UserDetails() {
    }

    public UserDetails(long id, String name, String email, String phone, String birthday, String password, String gender, String score) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.birthday = birthday;
        this.password = password;
        this.gender = gender;
        this.score = score;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
