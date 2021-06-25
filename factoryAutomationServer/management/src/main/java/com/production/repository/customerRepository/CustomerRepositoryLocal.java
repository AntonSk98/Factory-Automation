package com.production.repository.customerRepository;

import com.production.entity.customerManagement.Customer;

import javax.ejb.Local;
import java.util.List;

@Local
public interface CustomerRepositoryLocal {

    List<Customer> getAllCustomers();

    Customer getCustomerById(int id);

    void saveCustomer(Customer customer);

    Customer getCustomerByUsernameAndHashPassword(String username, String hashPassword);

    Customer getCustomerByUsername(String username);

    void approveCustomerById(int id);

    List<Integer> getAllNotApprovedCustomersIds();

    List<Integer> getAllApprovedCustomersIds();
}
