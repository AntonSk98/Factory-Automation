package com.productionFactory.repository.productRepository;

import com.productionFactory.entity.productEntity.Product;
import com.productionFactory.entity.productEntity.ProductState;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class ProductRepository implements ProductRepositoryLocal {
    @PersistenceContext(name = "ProductionPersistence")
    private EntityManager entityManager;

    @Override
    public void saveProduct(Product product) {
        entityManager.persist(product);
    }

    @Override
    public List<Integer> getClientIdsWaitingForDelivery() {
        return entityManager.createQuery("select product.clientId from Product product where product.productState =:productState", Integer.class)
                .setParameter("productState", ProductState.CREATED).getResultList().stream().distinct().collect(Collectors.toList());
    }

    @Override
    public void changeProductStateByClientId(ProductState productState, int clientId) {
        entityManager.createQuery("update Product product set product.productState =:productState where product.clientId =:clientId")
                .setParameter("productState", productState)
                .setParameter("clientId", clientId)
                .executeUpdate();
    }

    @Override
    public List<Product> getAllDeliveredProductsByClientId(int clientId) {
        return entityManager.createQuery("select product from Product product where product.productState =:productState and product.clientId =:clientId", Product.class)
                .setParameter("clientId", clientId)
                .setParameter("productState", ProductState.DELIVERED)
                .getResultList();
    }

    @Override
    public List<Product> getAllNotDeliveredProductsByClientId(int clientId) {
        return entityManager.createQuery("select product from Product product where product.productState =:productState and product.clientId =:clientId", Product.class)
                .setParameter("productState", ProductState.CREATED)
                .setParameter("clientId", clientId)
                .getResultList();
    }
}
