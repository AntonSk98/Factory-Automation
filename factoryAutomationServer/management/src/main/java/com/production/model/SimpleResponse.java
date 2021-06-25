package com.production.model;

import java.io.Serializable;

public class SimpleResponse implements Serializable {
    private boolean isOpSuccessful;
    private String value;

    public SimpleResponse(boolean isOpSuccessful, String value) {
        this.isOpSuccessful = isOpSuccessful;
        this.value = value;
    }

    public boolean getIsOpSuccessful() {
        return isOpSuccessful;
    }

    public void setIsOpSuccessful(boolean isOpSuccessful) {
        this.isOpSuccessful = isOpSuccessful;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
