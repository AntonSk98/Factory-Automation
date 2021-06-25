package com.production.entity.orderManagement;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "orders")
@DiscriminatorColumn(name="orderType",
        discriminatorType = DiscriminatorType.STRING)
public abstract class Order extends Essence implements Serializable {

    private int amount;
    private int price;

    public Order(int amount, int clientId) {
        super(clientId);
        this.amount = amount;
    }

    public Order() {
        super();
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Order{" +
                "amount=" + amount +
                ", price=" + price +
                '}';
    }
}
