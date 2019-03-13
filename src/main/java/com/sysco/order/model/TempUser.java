package com.sysco.order.model;

import org.springframework.data.annotation.Id;

public class TempUser {
    @Id
    private String id;


    private String userName;
    private String password;

    public TempUser(){
        this.userName = "";
        this.password = "";

    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getpassword() {

        return password;
    }

    public void setpassword(String password) {

        this.password  = password;
    }

    public boolean matchPassword(String inputPassword){
        return (this.password==inputPassword);
    }
    @Override
    public String toString() {
        return String.format("{id:%s, userName:%s, password:%s}", id, userName, password);
    }
}
