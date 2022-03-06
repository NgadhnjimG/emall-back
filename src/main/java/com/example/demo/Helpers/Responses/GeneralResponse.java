package com.example.demo.Helpers.Responses;

import com.example.demo.auth.JwtResponse;

import java.io.Serializable;

public class GeneralResponse<T> implements Serializable {
    private T data;
    private String token;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public GeneralResponse(T data, String token) {
        this.data = data;
        this.token = token;
    }

}
