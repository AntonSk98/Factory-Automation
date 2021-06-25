package com.production.service.salesManager;

import com.production.entity.customerManagement.Customer;
import com.production.model.SimpleResponse;

import javax.ejb.Local;
import java.util.List;

@Local
public interface SalesManagerServiceLocal {
    List<Customer> getAllCustomers();
    List<Integer> getAllNotApprovedCustomersIds();
    List<Integer> getAllApprovedCustomersId();
    SimpleResponse approveCustomerById(int id);
}
