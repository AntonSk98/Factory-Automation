package com.production.entity.orderManagement;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("scan_document_order")
public class ScanDocumentOrder extends Order {

    public ScanDocumentOrder(int amount, int clientId) {
        super(amount, clientId);
        super.setPrice(3);
    }

    public ScanDocumentOrder() {

    }
}
