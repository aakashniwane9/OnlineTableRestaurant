package com.example.eatheaven.Firebase;

import java.util.HashMap;
import java.util.Map;

public class UserFirebase {
    //Register
    String table,fullname,email,password,contactnumber,BusinessEmail,uid;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    //Business Details
    String BusinessName,GSTIN,Industry,Scale,Nature,City,Haves,Wants;


    public long contact_number=0;

    //String BusinessEmail;
    //Getters & Setters
    public UserFirebase() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public UserFirebase(String table, String fullname, String email, String password) {
        this.table = table;
        this.fullname = fullname;
        this.email = email;
        this.password = password;
    }

    public void setTable(String username) {
        this.table = username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
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



    //MAPS
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("table",table);
        result.put("name",fullname);
        result.put("email",email);
        result.put("password",password);
        return result;
    }
}
