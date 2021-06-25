package com.production.service.customerRegistry;

import com.production.entity.customerManagement.Customer;
import com.production.model.UserSessionResponse;

import javax.ejb.Local;
import java.util.List;

@Local
public interface CustomerRegistryServiceLocal {
    UserSessionResponse signIn(String username, String password);
    UserSessionResponse signUp(String username, String password, String street, String city, String country);
    List<Customer> getAllCustomers();}
