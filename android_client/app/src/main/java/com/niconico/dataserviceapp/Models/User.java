package com.niconico.dataserviceapp.Models;

import java.util.Date;

public class User {
    private long id;
    private String email;
    private String password;
    private boolean sexe;
    private Date birthdate;
    private String size;
    private String shoe_size;


    public User() {}

    public User(long id, String email, String password, boolean sexe, Date birthdate, String size, String shoe_size) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.sexe = sexe;
        this.birthdate = birthdate;
        this.size = size;
        this.shoe_size = shoe_size;
    }


    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getSexe() {
        return sexe;
    }
    public void setSexe(boolean sexe) {
        this.sexe = sexe;
    }

    public Date getBirthdate() {
        return birthdate;
    }
    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getSize() {
        return size;
    }
    public void setSize(String size) {
        this.size = size;
    }

    public String getShoe_size() {
        return shoe_size;
    }
    public void setShoe_size(String shoe_size) {
        this.shoe_size = shoe_size;
    }
}
