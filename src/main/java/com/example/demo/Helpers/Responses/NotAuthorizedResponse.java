package com.example.demo.Helpers.Responses;

import java.io.Serializable;
import java.sql.Timestamp;

public class NotAuthorizedResponse implements Serializable {
    private String message;
    private Timestamp timestamp=new Timestamp(System.currentTimeMillis());
    private int status;
    private String error="Unauthorized";

    public NotAuthorizedResponse() {
    }

    public NotAuthorizedResponse(String message, int status) {
        this.message = message;
        this.status = status;
    }

    public NotAuthorizedResponse(NotAuthorizedBuilder notAuthorizedBuilder){
        this.message=notAuthorizedBuilder.message;
        this.status=notAuthorizedBuilder.status;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public static class NotAuthorizedBuilder{
        private String message;
        private int status;

        public NotAuthorizedBuilder(String message,int status){
            this.message=message;
            this.status=status;
        }

        public NotAuthorizedBuilder(){

        }

        public NotAuthorizedBuilder setString(String message){
            this.message=message;
            return this;
        }

        public NotAuthorizedBuilder setStatus(int status){
            this.status=status;
            return this;
        }

        public NotAuthorizedResponse build(){
            return new NotAuthorizedResponse(this);
        }

    }
}
