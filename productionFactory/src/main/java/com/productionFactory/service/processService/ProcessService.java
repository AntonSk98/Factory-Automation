package com.productionFactory.service.processService;

import com.productionFactory.repository.processRepository.ProcessRepositoryLocal;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class ProcessService implements ProcessServiceLocal {

    @EJB
    ProcessRepositoryLocal processRepository;

    @Override
    public boolean createProcessesFromClientOrdersByClientId(int clientId) {
        return processRepository.createProcessesFromClientOrdersByClientId(clientId);
    }

    @Override
    public void startProductCreation() {

    }
}
