package com.jalian.planter.request;

public class SendRequest {

    private String potId;
    private String value;

    public SendRequest () {}

    public SendRequest(String potId, String value) {
        this.potId = potId;
        this.value = value;
    }

    public String getPotId() {
        return potId;
    }

    public void setPotId(String potId) {
        this.potId = potId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
