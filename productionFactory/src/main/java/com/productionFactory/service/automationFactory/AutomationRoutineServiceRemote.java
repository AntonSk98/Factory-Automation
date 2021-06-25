package com.productionFactory.service.automationFactory;

import com.productionFactory.entity.productEntity.Product;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface AutomationRoutineServiceRemote {
    List<Product> startAutomationFactoryRoutine();
}
