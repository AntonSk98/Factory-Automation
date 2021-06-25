package com.production.repository.orderRepository;

import com.production.entity.orderManagement.Order;
import com.production.model.SimpleResponse;

import javax.ejb.Local;
import java.util.List;

@Local
public interface OrderRepositoryLocal {
    SimpleResponse addService(Order order);
    SimpleResponse removeService(Order order);
    SimpleResponse removeAllServices();
    List<Order> getAllServices();
    List<Order> getAllServicesByUserId(int userId);
    List<Integer> getNotProcessedOrdersUserIds();
    void addMultiple(List<Order> orders);
    void removeServiceByClientId(int clientId);
}
