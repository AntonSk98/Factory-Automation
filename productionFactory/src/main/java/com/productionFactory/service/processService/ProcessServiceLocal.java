package com.productionFactory.service.processService;

import javax.ejb.Local;

@Local
public interface ProcessServiceLocal {
    boolean createProcessesFromClientOrdersByClientId(int clientId);
    void startProductCreation();
}
