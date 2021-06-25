package com.production.service.customerRegistry;

import com.production.entity.customerManagement.Address;
import com.production.entity.customerManagement.Customer;
import com.production.model.UserSessionResponse;
import com.production.repository.customerRepository.CustomerRepository;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Stateless
@LocalBean
public class CustomerRegistryService implements CustomerRegistryServiceLocal {

    @EJB
    private CustomerRepository customerRepository;

    @Override
    public UserSessionResponse signIn(String username, String password) {
        String hashPassword = generateSHA256Password(password);
        Customer customer = customerRepository.getCustomerByUsernameAndHashPassword(username, hashPassword);
        if (customer == null) {
            return new UserSessionResponse(false, "Signing in operation failed");
        }
        if (!customer.isApproved()) {
            return new UserSessionResponse(false, "Your account is not approved by our sale manager yet!");
        }
        return new UserSessionResponse(true, "Great! Welcome to our Automation Fabric!", customer.getId());
    }

    @Override
    public UserSessionResponse signUp(String username, String password, String street, String city, String country) {
        Address address = new Address(street, city, country);
        if (customerRepository.getCustomerByUsername(username) != null) {
            return new UserSessionResponse(false, "The user with the same username is already registered");
        } else {
            Customer newCustomer = new Customer(username, generateSHA256Password(password), address);
            customerRepository.saveCustomer(newCustomer);
            return new UserSessionResponse(true, "Thanks for the registration. Please wait until our sale manager approves your account", newCustomer.getId());
        }
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.getAllCustomers();
    }

    public String generateSHA256Password(String password) {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        messageDigest.update(password.getBytes());
        return new String(messageDigest.digest());
    }

}
