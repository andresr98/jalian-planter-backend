package com.jalian.planter.exception.msg;

public class ResponseMessage {

    private String message;

    public ResponseMessage(String msg) {
        this.message = msg;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
