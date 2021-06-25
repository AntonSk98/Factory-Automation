package com.productionFactory.repository.processRepository;

import com.productionFactory.entity.processEntity.Process;
import com.productionFactory.entity.processEntity.ProcessState;

import javax.ejb.Local;
import java.util.List;

@Local
public interface ProcessRepositoryLocal {
   boolean createProcessesFromClientOrdersByClientId(int clientId);
   List<Process> getAllPendingProcesses();
   void changeProcessesState(ProcessState processState);
}
