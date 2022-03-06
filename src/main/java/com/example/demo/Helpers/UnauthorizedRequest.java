package com.example.demo.Helpers;

import java.io.Serializable;

public class UnauthorizedRequest implements Serializable {
    private final String message;

    public UnauthorizedRequest(String message) {
        this.message= message;
    }

    public String getMessage() {
        return message;
    }
}
