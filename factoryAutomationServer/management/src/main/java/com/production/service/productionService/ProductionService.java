package com.production.service.productionService;

import com.production.utils.HttpGet;

import javax.ejb.Stateless;

@Stateless
public class ProductionService implements ProductionServiceLocal {
    @Override
    public String createProductsByClientId(int clientId) {
        return HttpGet.fetchSyncGetRequest("http://localhost:8080/productionFactory-1.0/api/production/" + clientId);
    }

    @Override
    public String getClientIdsWaitingForShipment() {
        return HttpGet.fetchSyncGetRequest("http://localhost:8080/productionFactory-1.0/api/production/shipment_waiting");
    }

    @Override
    public String shipProductByClientId(int clientId) {
        return HttpGet.fetchSyncGetRequest("http://localhost:8080/productionFactory-1.0/api/production/shipment/" + clientId);
    }

    @Override
    public String sendInvoiceByClientId(int clientId) {
        return HttpGet.fetchSyncGetRequest("http://localhost:8080/productionFactory-1.0/api/production/invoice/" + clientId);
    }
}
