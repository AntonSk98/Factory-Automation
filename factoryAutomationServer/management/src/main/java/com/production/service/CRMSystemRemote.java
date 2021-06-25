package com.production.service;

import com.production.entity.customerManagement.Customer;
import com.production.entity.orderManagement.Order;
import com.production.model.SimpleResponse;
import com.production.model.UserSessionResponse;

import javax.ejb.Remote;
import java.io.IOException;
import java.util.List;

@Remote
public interface CRMSystemRemote {

    // customer related
    UserSessionResponse signIn(String username, String password);
    UserSessionResponse signUp(String username, String password, String street, String city, String country);
    List<Customer> getAllCustomers();

    // sales manager related
    List<Integer> getAllNotApprovedCustomersIds();
    List<Integer> getAllApprovedCustomersId();
    SimpleResponse approveCustomerById(int id);

    //order related
    List<Order> getAllServicesByClientId(int userId);
    SimpleResponse submitOrder(int clientId, List<Order> orders);
    List<Integer> getUserIdsWaitingToProcessOrder();

    //products related
    String startProductionByClientId(int clientId) throws IOException;
    String getClientIdsWaitingForShipment();
    String shipProductByClientId(int clientId);
    String sendInvoiceByClientId(int clientId);


}
