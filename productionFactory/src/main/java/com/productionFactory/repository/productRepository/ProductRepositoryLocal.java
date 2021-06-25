package com.productionFactory.repository.productRepository;

import com.productionFactory.entity.productEntity.Product;
import com.productionFactory.entity.productEntity.ProductState;

import javax.ejb.Local;
import java.util.List;

@Local
public interface ProductRepositoryLocal {
    void saveProduct(Product product);

    List<Integer> getClientIdsWaitingForDelivery();

    void changeProductStateByClientId(ProductState productState, int clientId);

    List<Product> getAllDeliveredProductsByClientId(int clientId);

    List<Product> getAllNotDeliveredProductsByClientId(int clientId);
}
