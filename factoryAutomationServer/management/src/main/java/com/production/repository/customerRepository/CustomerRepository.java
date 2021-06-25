package com.production.repository.customerRepository;

import com.production.entity.customerManagement.Customer;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateful
@LocalBean
public class CustomerRepository implements CustomerRepositoryLocal {

    @PersistenceContext(name = "CRMPersistence")
    private EntityManager entityManager;


    public CustomerRepository() {
    }


    @Override
    public List<Customer> getAllCustomers() {
        return entityManager.createQuery("select customers from Customer customers", Customer.class).getResultList();

    }

    @Override
    public Customer getCustomerById(int id) {
        return entityManager.createQuery("select customer from Customer  customer where customer.id =:id", Customer.class)
                .setParameter("id", id).getResultList().stream().findFirst().orElse(null);
    }

    @Override
    public void saveCustomer(Customer customer) {
        entityManager.persist(customer);
    }

    @Override
    public Customer getCustomerByUsernameAndHashPassword(String username, String hashPassword) {
        return entityManager.createQuery("select customer from Customer  customer where customer.username =:username and customer.password =:password", Customer.class)
                .setParameter("username", username)
                .setParameter("password", hashPassword)
                .getResultList().stream().findFirst().orElse(null);

    }

    @Override
    public Customer getCustomerByUsername(String username) {
        return entityManager.createQuery("select customer from Customer customer where customer.username =:username", Customer.class)
                .setParameter("username", username).getResultList().stream().findFirst().orElse(null);
    }

    @Override
    public void approveCustomerById(int id) {
        entityManager.createQuery("update Customer customer set customer.approved = true ", Customer.class).executeUpdate();
    }

    @Override
    public List<Integer> getAllNotApprovedCustomersIds() {
        return entityManager.createQuery("select customer.id from Customer customer where customer.approved = false", Integer.class).getResultList();
    }

    @Override
    public List<Integer> getAllApprovedCustomersIds() {
        return entityManager.createQuery("select customer.id from Customer customer where customer.approved = true", Integer.class).getResultList();
    }
}
