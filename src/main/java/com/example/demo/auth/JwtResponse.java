package com.example.demo.auth;

import java.io.Serializable;

public class JwtResponse implements Serializable {

    private static final long serialVersionUID = -8091879091924046844L;
    private final String jwttoken;
    private final int userID;

    public JwtResponse(String jwttoken,int userID) {
        this.jwttoken = jwttoken;
        this.userID=userID;
    }

    public int getUserID() {
        return userID;
    }

    public String getToken() {
        return this.jwttoken;
    }
}
