package com.productionFactory.repository.processRepository;

import com.productionFactory.entity.processEntity.Process;
import com.productionFactory.entity.processEntity.ProcessState;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class ProcessRepository implements ProcessRepositoryLocal{
    @PersistenceContext(name = "ProductionPersistence")
    private EntityManager entityManager;

    @Override
    public boolean createProcessesFromClientOrdersByClientId(int clientId) {
        List<Object[]> objResponseList = entityManager.createNativeQuery("select * from Orders where clientid =:clientId")
                .setParameter("clientId", clientId)
                .getResultList();
        if (objResponseList.size() == 0)
            return false;
        objResponseList.forEach(objectResponse -> {
            entityManager.persist(new Process(objectResponse[0]+"_process", (Integer) objectResponse[3], (Integer) objectResponse[4], (Integer) objectResponse[2]));
        });
        return true;
    }

    @Override
    public List<Process> getAllPendingProcesses() {
        return entityManager.createQuery("select processes from Process processes where processes.processState =:processState", Process.class)
                .setParameter("processState", ProcessState.PENDING)
                .getResultList();
    }

    @Override
    public void changeProcessesState(ProcessState processState) {
        entityManager.createQuery("update Process process set process.processState =:processState")
                .setParameter("processState", processState)
                .executeUpdate();
    }
}
