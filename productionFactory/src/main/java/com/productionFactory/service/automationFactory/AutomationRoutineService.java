package com.productionFactory.service.automationFactory;

import com.productionFactory.entity.processEntity.Process;
import com.productionFactory.entity.processEntity.ProcessState;
import com.productionFactory.entity.productEntity.Product;
import com.productionFactory.repository.processRepository.ProcessRepositoryLocal;
import com.productionFactory.service.production.MachineProductionManagerLocal;

import javax.ejb.*;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

@Stateless
public class AutomationRoutineService implements AutomationRoutineServiceRemote {

    @EJB
    ProcessRepositoryLocal processRepository;
    @EJB
    MachineProductionManagerLocal machineProduction;

    @Override
    public List<Product> startAutomationFactoryRoutine() {
        List<Process> processes = processRepository.getAllPendingProcesses();
        processRepository.changeProcessesState(ProcessState.STARTED);
        List<Product> products = new ArrayList<>();
        processes.forEach(process -> products.add(machineProduction.createProductByProcess(process)));
        processRepository.changeProcessesState(ProcessState.FINISHED);
        return products;
    }

}
