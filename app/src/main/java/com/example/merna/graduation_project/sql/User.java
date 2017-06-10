package com.example.merna.graduation_project.sql;

/**
 * Created by merna on 5/21/2017.
 */

public class User {


    private String id;
    private String name;
    private String email;
    private String password;
    private String PhoneNumber;


    public String getGmail() {
        return email;
    }

    public void setGmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String PhoneNumber) {
        this.PhoneNumber = PhoneNumber;
    }

    public String getId() {

        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}