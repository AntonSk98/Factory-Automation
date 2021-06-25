package com.production.service.order;

import com.production.entity.orderManagement.Order;
import com.production.model.SimpleResponse;

import javax.ejb.Local;
import java.io.IOException;
import java.util.List;

@Local
public interface OrderServiceLocal {
    void addService(Order order);
    List<Order> getAllServicesByClientId(int userId);

    SimpleResponse submitOrder(int clientId, List<Order> orders);

    List<Integer> getNotProcessedOrdersUserIds();
    String startProductionByClientId(int clientId) throws IOException;

}
