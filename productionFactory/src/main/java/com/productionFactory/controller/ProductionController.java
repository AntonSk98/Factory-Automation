package com.productionFactory.controller;

import com.productionFactory.service.automationFactory.AutomationRoutineServiceRemote;
import com.productionFactory.service.processService.ProcessServiceLocal;
import com.productionFactory.service.productService.ProductServiceLocal;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Application;

@ApplicationPath("/api")
@Path("/production")
public class ProductionController extends Application {

    @EJB
    ProcessServiceLocal processService;
    @EJB
    AutomationRoutineServiceRemote automationRoutineService;

    @EJB
    ProductServiceLocal productService;

    //http://localhost:8080/productionFactory-1.0/api/production/{clientId}
    @GET()
    @Path("/{clientId}")
    @Produces("text/plain")
    public String generateProduct(@PathParam("clientId") Integer clientId) {
        if (!processService.createProcessesFromClientOrdersByClientId(clientId))
            return "There are no orders yet available for the client with id: " + clientId;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("The following products were created and placed in the storage...\n");
        automationRoutineService.startAutomationFactoryRoutine()
                .forEach(product -> stringBuilder.append(product.toString()).append("\n"));
        return stringBuilder.toString();
    }

    //http://localhost:8080/productionFactory-1.0/api/production/shipment_waiting
    @GET
    @Path("/shipment_waiting")
    @Produces("text/plain")
    public String getClientIdsWaitingForShipment() {
        StringBuilder stringBuilder = new StringBuilder();
        productService.getClientIdsWaitingForDelivery()
                .forEach(clientId -> stringBuilder.append(clientId).append("\n"));
        return stringBuilder.toString();
    }

    //http://localhost:8080/productionFactory-1.0/api/production/shipment/{clientId}
    @GET
    @Path("/shipment/{clientId}")
    @Produces("text/plain")
    public String shipProduct(@PathParam("clientId") int clientId) {
        return productService.deliverProductsByClientId(clientId);
    }

    //http://localhost:8080/productionFactory-1.0/api/production/invoice/{clientId}
    @GET
    @Path("/invoice/{clientId}")
    public String sendInvoice(@PathParam("clientId") int clientId) {
        return productService.generateInvoiceByClientId(clientId);
    }
}