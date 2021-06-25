package com.productionFactory.service.production;

import com.productionFactory.entity.processEntity.Process;
import com.productionFactory.entity.productEntity.Product;
import com.productionFactory.service.production.productionMachines.DocumentMachineProductionLocal;
import com.productionFactory.service.production.productionMachines.PhotoMachineProductionLocal;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class MachineProductionManagerManager implements MachineProductionManagerLocal {

    @EJB
    DocumentMachineProductionLocal documentMachineProduction;

    @EJB
    PhotoMachineProductionLocal photoMachineProduction;

    @Override
    public Product createProductByProcess(Process process) {
        if (process.getProcessName().contains("photo")) {
            return photoMachineProduction.createProductByProcess(process);
        }
        if (process.getProcessName().contains("document")) {
            return documentMachineProduction.createProductByProcess(process);
        }
        return null;
    }
}
