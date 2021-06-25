package com.productionFactory.service.production.productionMachines;

import com.productionFactory.entity.productEntity.Product;
import com.productionFactory.repository.productRepository.ProductRepositoryLocal;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class RobotArms implements RobotArmsLocal {

    @EJB
    ProductRepositoryLocal productRepository;

    @Override
    public void moveProductToStorage(Product product) {
        productRepository.saveProduct(product);
    }
}
