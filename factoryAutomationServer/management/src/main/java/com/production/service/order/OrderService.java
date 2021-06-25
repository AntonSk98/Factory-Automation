package com.production.service.order;

import com.production.entity.orderManagement.Order;
import com.production.model.SimpleResponse;
import com.production.repository.orderRepository.OrderRepository;
import com.production.service.productionService.ProductionServiceLocal;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.io.*;
import java.util.List;

@Stateless
@LocalBean
public class OrderService implements OrderServiceLocal {

    @EJB
    OrderRepository orderRepository;
    @EJB
    ProductionServiceLocal productionService;


    @Override
    public void addService(Order order) {
        orderRepository.addService(order);
    }

    @Override
    public List<Order> getAllServicesByClientId(int clientId) {
        return orderRepository.getAllServicesByUserId(clientId);
    }

    @Override
    public SimpleResponse submitOrder(int clientId, List<Order> orders) {
        orderRepository.addMultiple(orders);
        System.out.println(orders);
        return new SimpleResponse(true, "Order is submitted. Our production manager will start it as soon as possible: ");
    }

    @Override
    public List<Integer> getNotProcessedOrdersUserIds() {
        return orderRepository.getNotProcessedOrdersUserIds();
    }

    @Override
    public String startProductionByClientId(int clientId) throws IOException {
        String responseBody = productionService.createProductsByClientId(clientId);
        orderRepository.removeServiceByClientId(clientId);
        return responseBody;

    }
}
