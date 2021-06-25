package com.production.entity.orderManagement;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
public class Essence implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "clientId", nullable = false)
    private int clientId;

    public Essence(int clientId) {
        this.clientId = clientId;
    }

    public Essence() {

    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    @Override
    public String toString() {
        return "Entity{" +
                "clientId=" + clientId +
                '}';
    }
}
