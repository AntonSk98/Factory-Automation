package com.production.entity.orderManagement;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("scan_photo_order")
public class ScanPhotoOrder extends Order {

    public ScanPhotoOrder(int amount, int clientId) {
        super(amount, clientId);
        super.setPrice(4);
    }

    public ScanPhotoOrder() {

    }
}
