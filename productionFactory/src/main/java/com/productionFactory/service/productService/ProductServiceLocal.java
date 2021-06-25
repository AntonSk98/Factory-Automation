package com.productionFactory.service.productService;

import javax.ejb.Local;
import java.util.List;

@Local
public interface ProductServiceLocal {
    List<Integer> getClientIdsWaitingForDelivery();
    String deliverProductsByClientId(int clientId);
    String generateInvoiceByClientId(int clientId);
}
