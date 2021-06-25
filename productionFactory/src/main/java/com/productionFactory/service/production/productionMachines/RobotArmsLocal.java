package com.productionFactory.service.production.productionMachines;

import com.productionFactory.entity.productEntity.Product;

import javax.ejb.Local;

@Local
public interface RobotArmsLocal {
    void moveProductToStorage(Product product);
}
