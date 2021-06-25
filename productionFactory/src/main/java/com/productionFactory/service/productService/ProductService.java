package com.productionFactory.service.productService;

import com.productionFactory.entity.productEntity.Product;
import com.productionFactory.entity.productEntity.ProductState;
import com.productionFactory.repository.productRepository.ProductRepositoryLocal;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Stateless
public class ProductService implements ProductServiceLocal {
    @EJB
    ProductRepositoryLocal productRepository;

    @Override
    public List<Integer> getClientIdsWaitingForDelivery() {
        return productRepository.getClientIdsWaitingForDelivery();
    }

    @Override
    public String deliverProductsByClientId(int clientId) {
        System.out.println("SHIPPING PRODUCT FOR THE CLIENT " + clientId);
        if (productRepository.getAllNotDeliveredProductsByClientId(clientId).size() == 0)
            return "There are no available products to deliver for the client with id: " + clientId;
        productRepository.changeProductStateByClientId(ProductState.SHIPPED, clientId);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        productRepository.changeProductStateByClientId(ProductState.DELIVERED, clientId);
        return "ProductsAreSuccessfullyDelivered!";
    }

    @Override
    public String generateInvoiceByClientId(int clientId) {
        AtomicInteger totalPrice = new AtomicInteger();
        StringBuilder stringBuilder = new StringBuilder();
        List<Product> deliveredProducts = productRepository.getAllDeliveredProductsByClientId(clientId);
        if (deliveredProducts.size() == 0)
            return "There are no delivered product in our system for the user with id: " + clientId;
        deliveredProducts.forEach(deliveredProduct -> {
            stringBuilder.append("Product: ").append(deliveredProduct.getProductName()).append("\t")
                    .append("Quantity: ").append(deliveredProduct.getQuantity()).append("\t")
                    .append("Price: ").append(deliveredProduct.getPrice()).append("\t")
                    .append("Total price per item: ").append(deliveredProduct.getQuantity() * deliveredProduct.getPrice()).append("\t")
                    .append("\n");
            totalPrice.addAndGet(deliveredProduct.getPrice() * deliveredProduct.getQuantity());
        });
        stringBuilder.append("TOTAL PRICE: ").append(totalPrice).append("\n");
        return stringBuilder.toString();
    }
}
