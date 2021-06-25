package com.production.model;

public class InvoiceResponse extends  SimpleResponse {

    private int clientId;
    private String username;

    public InvoiceResponse(int clientId, String username, boolean isOpSuccessful, String value) {
        super(isOpSuccessful, value);
        this.clientId = clientId;
        this.username = username;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
