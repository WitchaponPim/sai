package com.example.tan.mtapp.Model;

public class UpPicModel {
    /**
     * success : true
     * message : http://pmis.lpn.co.th/2.PNG
     */

    private boolean success;
    private String message;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
