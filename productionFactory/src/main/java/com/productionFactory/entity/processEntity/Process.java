package com.productionFactory.entity.processEntity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "process")
public class Process implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String processName;
    private int quantity;
    private int price;
    @Column(nullable = false)
    private int clientId;
    private ProcessState processState;

    public Process() {
    }

    public Process(String processName, int quantity, int price, int clientId) {
        this.processName = processName;
        this.quantity = quantity;
        this.price = price;
        this.clientId = clientId;
        this.processState = ProcessState.PENDING;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public ProcessState getProcessState() {
        return processState;
    }

    public void setProcessState(ProcessState processState) {
        this.processState = processState;
    }

    @Override
    public String toString() {
        return "Process{" +
                "id=" + id +
                ", processName='" + processName + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", processState=" + processState +
                '}';
    }

    public int getClientId() {
        return this.clientId;
    }
}
