package com.production.repository.orderRepository;

import com.production.entity.orderManagement.Order;
import com.production.model.SimpleResponse;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
@LocalBean
public class OrderRepository implements OrderRepositoryLocal {

    @PersistenceContext(name = "CRMPersistence")
    private EntityManager entityManager;

    public OrderRepository() {
    }

    @Override
    public SimpleResponse addService(Order order) {
        try {
            entityManager.persist(order);
        } catch (Exception e) {
            return new SimpleResponse(false, "Could not add the order...");
        }
        return new SimpleResponse(true, "The order is added successfully!");
    }

    @Override
    public SimpleResponse removeService(Order order) {
        try {
            entityManager.remove(order);
        } catch (Exception e) {
            return new SimpleResponse(false, "Could not remove the order...");
        }
        return new SimpleResponse(true, "The order is removed successfully!");
    }

    @Override
    public SimpleResponse removeAllServices() {
        entityManager.createQuery("delete from Order").executeUpdate();
        return new SimpleResponse(true, "All orders are removed!");
    }

    public List<Order> getAllServices() {
        return entityManager.createQuery("select order from Order order", Order.class)
                .getResultList();
    }

    @Override
    public List<Order> getAllServicesByUserId(int clientId) {
        return entityManager.createQuery("select order from Order order where order.clientId =:clientId", Order.class)
                .setParameter("clientId", clientId)
                .getResultList();
    }

    @Override
    public List<Integer> getNotProcessedOrdersUserIds() {
        return entityManager.createQuery("select order.clientId from Order order", Integer.class)
                .getResultList().stream().distinct().collect(Collectors.toList());
    }

    @Override
    public void addMultiple(List<Order> orders) {
        orders.forEach(order -> entityManager.persist(order));
    }


    @Override
    public void removeServiceByClientId(int clientId) {
        if (getAllServicesByUserId(clientId).size() == 0)
            return;
        System.out.println("I FAIL ACTUALLY HERE...");
        entityManager.createQuery("delete from Order where clientId =:clientId")
                .setParameter("clientId", clientId)
                .executeUpdate();
    }
}
