package com.production.model;

public class UserSessionResponse extends SimpleResponse{

    private int userId;

    public UserSessionResponse(boolean isOpSuccessful, String value) {
        super(isOpSuccessful, value);
    }

    public UserSessionResponse(boolean isOpSuccessful, String value, int userId) {
        super(isOpSuccessful, value);
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
