package com.sysco.order.model;

import org.springframework.data.annotation.Id;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class User {

    @Id
    private String id;


    private String userName;
    private String password;

    public User(){
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
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        this.password  = passwordEncoder.encode(password);
    }

    public boolean matchPassword(String inputPassword){

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        return(passwordEncoder.matches(inputPassword,this.password));

    }
    @Override
    public String toString() {
        return String.format("{id:%s, userName:%s, password:%s}", id, userName, password);
    }
}
