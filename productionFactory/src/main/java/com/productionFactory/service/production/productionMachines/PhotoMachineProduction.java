package com.productionFactory.service.production.productionMachines;

import com.productionFactory.entity.processEntity.Process;
import com.productionFactory.entity.productEntity.Product;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

@Stateless
@LocalBean
public class PhotoMachineProduction implements PhotoMachineProductionLocal {

    @EJB(beanName = "RobotArms")
    RobotArmsLocal robotArms;

    @Override
    public Product createProductByProcess(Process process) {
        System.out.println("Machine with the name " + this.getClass().getSimpleName() + "creates the product....");
        try {
            Thread.sleep(2000); // simulate creation...
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Product product = new Product(process.getProcessName().replace("_order_process", "_productPhoto"), process.getPrice(), process.getQuantity(), process.getClientId());
        robotArms.moveProductToStorage(product);
        return product;
    }
}
