package com.example.demo.LogsDefinition;

import javax.sound.midi.SysexMessage;
import java.util.UUID;

public class SystemLog {
    private UUID logID;
    private int userID;
    private String path;
    private boolean isSuccessful;

    public SystemLog(int userID, String path, boolean isSuccessful) {
        this.userID = userID;
        this.path = path;
        this.isSuccessful = isSuccessful;
    }

    public SystemLog(SystemLogBuilder builder){
        this.logID =builder.LogID;
        this.userID =builder.UserID;
        this.path= builder.path;
        this.isSuccessful=builder.isSuccessful;
    }

    public SystemLog() {
    }

    public UUID getLogID() {
        return logID;
    }

    public void setLogID(UUID logID) {
        this.logID = logID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public boolean isSuccessful() {
        return isSuccessful;
    }

    public void setSuccessful(boolean successful) {
        isSuccessful = successful;
    }

    @Override
    public String toString() {
        return "Log "+this.getLogID()+": "+this.getUserID()+" has requested "+this.getPath()+" .";
    }

    public static class SystemLogBuilder{
        private UUID LogID;
        private int UserID;
        private String path;
        private boolean isSuccessful;

        public SystemLogBuilder(){

        }

        public SystemLog build(){
            return new SystemLog(this);
        }

        public SystemLogBuilder setLogID(UUID logID) {
            LogID = logID;
            return this;
        }

        public SystemLogBuilder setUserID(int userID) {
            UserID = userID;
            return this;
        }

        public SystemLogBuilder setPath(String path) {
            this.path = path;
            return this;
        }

        public SystemLogBuilder setSuccessful(boolean successful) {
            isSuccessful = successful;
            return this;
        }
    }
}
