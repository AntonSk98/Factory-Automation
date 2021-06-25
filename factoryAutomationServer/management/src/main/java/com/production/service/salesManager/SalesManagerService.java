package com.production.service.salesManager;

import com.production.entity.customerManagement.Customer;
import com.production.model.SimpleResponse;
import com.production.repository.customerRepository.CustomerRepository;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
@LocalBean
public class SalesManagerService implements SalesManagerServiceLocal {

    @EJB(beanName = "CustomerRepository")
    private CustomerRepository customerRepository;


    @Override
    public List<Customer> getAllCustomers() {
        System.out.println("FROM SALES MANAGER SERVICE");
        System.out.println(customerRepository.getAllCustomers());
        return customerRepository.getAllCustomers();
    }

    @Override
    public List<Integer> getAllNotApprovedCustomersIds() {
        return customerRepository.getAllNotApprovedCustomersIds();
    }

    @Override
    public List<Integer> getAllApprovedCustomersId() {
        return customerRepository.getAllApprovedCustomersIds();
    }

    @Override
    public SimpleResponse approveCustomerById(int id) {
        Customer customer = customerRepository.getCustomerById(id);
        if (customer == null) {
            return new SimpleResponse(false, "The customer with id: " + id + " does not exist!");
        }
        customer.setApproved(true);
        return new SimpleResponse(true, "The customer with id: " + id + " is successfully approved and can log in now!");
    }
}
