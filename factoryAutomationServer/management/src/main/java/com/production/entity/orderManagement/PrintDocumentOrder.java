package com.production.entity.orderManagement;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("print_document_order")
public class PrintDocumentOrder extends Order {
    public PrintDocumentOrder(int amount, int clientId) {
        super(amount, clientId);
        super.setPrice(1);
    }

    public PrintDocumentOrder() {

    }
}
