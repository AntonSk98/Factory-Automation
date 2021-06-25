package com.productionFactory.service.production.productionMachines;

import com.productionFactory.entity.processEntity.Process;
import com.productionFactory.entity.productEntity.Product;

import javax.ejb.Local;

@Local
public interface DocumentMachineProductionLocal {
    Product createProductByProcess(Process process);
}
