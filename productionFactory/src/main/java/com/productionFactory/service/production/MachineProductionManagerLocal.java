package com.productionFactory.service.production;

import com.productionFactory.entity.processEntity.Process;
import com.productionFactory.entity.productEntity.Product;

import javax.ejb.Local;

@Local
public interface MachineProductionManagerLocal {
    Product createProductByProcess(Process process);
}
