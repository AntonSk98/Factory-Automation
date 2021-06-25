package com.production.service.shipment;//package com.antonsk98.service.shipment;
//
//
//import com.antonsk98.entity.productManagement.State;
//import com.antonsk98.model.SimpleResponse;
//import com.antonsk98.repository.customerRepository.CustomerRepository;
//import com.antonsk98.repository.productRepository.ProductRepository;
//
//import javax.ejb.EJB;
//import javax.ejb.LocalBean;
//import javax.ejb.Stateless;
//
//@Stateless
//@LocalBean
//public class ShipmentService implements ShipmentServiceLocal {
//
//    @EJB
//    ProductRepository productRepository;
//
//    @EJB
//    CustomerRepository customerRepository;
//
//
//    @Override
//    public SimpleResponse shipProductsToCustomById(int customerId) {
//        productRepository.getAllNotDeliveredProductsByUserId(customerId)
//                .forEach(product -> product.setState(State.SHIPPING));
//        try {
//            Thread.sleep(2000); // imitating the shipping process
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        productRepository.getAllNotDeliveredProductsByUserId(customerId).forEach(product -> product.setState(State.DELIVERED));
//        return new SimpleResponse(true, "The product are successfully delivered to the client: " + customerRepository.getCustomerById(customerId).getUsername());
//    }
//}
