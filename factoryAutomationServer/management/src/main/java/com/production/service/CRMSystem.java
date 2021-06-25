package com.production.service;

import com.production.entity.customerManagement.Customer;
import com.production.entity.orderManagement.Order;
import com.production.model.SimpleResponse;
import com.production.model.UserSessionResponse;
import com.production.service.customerRegistry.CustomerRegistryServiceLocal;
import com.production.service.order.OrderServiceLocal;
import com.production.service.productionService.ProductionServiceLocal;
import com.production.service.salesManager.SalesManagerServiceLocal;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.io.IOException;
import java.util.List;

@Stateless
@LocalBean
public class CRMSystem implements CRMSystemRemote {

    @EJB
    CustomerRegistryServiceLocal customerRegistryService;
    @EJB
    SalesManagerServiceLocal salesManagerService;
    @EJB
    OrderServiceLocal orderService;

    @EJB
    ProductionServiceLocal productionService;


    @Override
    public UserSessionResponse signIn(String username, String password) {
        return customerRegistryService.signIn(username, password);
    }

    @Override
    public UserSessionResponse signUp(String username, String password, String street, String city, String country) {
        return customerRegistryService.signUp(username, password, street, city, country);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRegistryService.getAllCustomers();
    }

    @Override
    public List<Integer> getAllNotApprovedCustomersIds() {
        return salesManagerService.getAllNotApprovedCustomersIds();
    }

    @Override
    public List<Integer> getAllApprovedCustomersId() {
        return salesManagerService.getAllApprovedCustomersId();
    }

    @Override
    public SimpleResponse approveCustomerById(int id) {
        return salesManagerService.approveCustomerById(id);
    }

    @Override
    public List<Order> getAllServicesByClientId(int userId) {
        return orderService.getAllServicesByClientId(userId);
    }

    @Override
    public SimpleResponse submitOrder(int clientId, List<Order> orders) {
        return orderService.submitOrder(clientId, orders);
    }

    @Override
    public List<Integer> getUserIdsWaitingToProcessOrder() {
        return orderService.getNotProcessedOrdersUserIds();
    }

    @Override
    public String startProductionByClientId(int clientId) throws IOException {
        return orderService.startProductionByClientId(clientId);
    }

    @Override
    public String getClientIdsWaitingForShipment() {
        return productionService.getClientIdsWaitingForShipment();
    }

    @Override
    public String shipProductByClientId(int clientId) {
        return productionService.shipProductByClientId(clientId);
    }

    @Override
    public String sendInvoiceByClientId(int clientId) {
        return productionService.sendInvoiceByClientId(clientId);
    }
}
