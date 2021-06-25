package com.production.service.productionService;

import javax.ejb.Local;

@Local
public interface ProductionServiceLocal {
    String createProductsByClientId(int clientId);
    String getClientIdsWaitingForShipment();
    String shipProductByClientId(int clientId);
    String sendInvoiceByClientId(int clientId);
}
