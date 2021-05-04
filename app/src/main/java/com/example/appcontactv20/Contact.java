package com.example.appcontactv20;

import java.io.Serializable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Contact implements Serializable {
    @PrimaryKey (autoGenerate = true)
    @ColumnInfo (name = "ID")
    private int id;
    @ColumnInfo (name = "Avatar")
    private int avatar;
    @ColumnInfo (name = "Full name")
    private String name;
    @ColumnInfo (name = "Phone number")
    private String mobile;
    @ColumnInfo (name = "Email address")
    private String email;

    public Contact(int id, String name, String mobile, String email, int avatar) {
        this.id = id;
        this.name = name;
        this.mobile = mobile;
        this.email = email;
        this.avatar = avatar;
    }

    public Contact() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }

}





