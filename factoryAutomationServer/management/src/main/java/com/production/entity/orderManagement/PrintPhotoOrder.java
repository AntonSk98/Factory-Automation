package com.production.entity.orderManagement;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("print_photo_order")
public class PrintPhotoOrder extends Order {
    public PrintPhotoOrder(int amount, int clientId) {
        super(amount, clientId);
        super.setPrice(2);
    }

    public PrintPhotoOrder() {

    }
}
