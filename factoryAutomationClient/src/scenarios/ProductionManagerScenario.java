package scenarios;


import com.production.service.CRMSystem;
import com.production.service.CRMSystemRemote;
import utils.BeanGenerator;

import javax.naming.NamingException;
import java.io.IOException;
import java.util.Scanner;

public class ProductionManagerScenario implements Scenario {

    CRMSystemRemote crmSystem = new BeanGenerator<CRMSystemRemote>().generateBean(CRMSystem.class, CRMSystemRemote.class);

    public ProductionManagerScenario() throws NamingException {
    }

    @Override
    public void playRole(Scanner userInput) throws IOException {
        int productManagerChoice = 0;
        System.out.println("Welcome product manager");
        System.out.println("There are all your available operations...");
        while (productManagerChoice != 3) {
            System.out.println("1 - View user ids that are waiting to process their orders");
            System.out.println("2 - Start process by entering client id");
            System.out.println("3 - Go back");
            productManagerChoice = userInput.nextInt();
            switch (productManagerChoice) {
                case 1:
                    System.out.println("User ids that are waiting to have their order processed: " + crmSystem.getUserIdsWaitingToProcessOrder());
                    break;
                case 2:
                    System.out.println("Type here the client id...");
                    int clientId = userInput.nextInt();
                    System.out.println("Please, be patient until we perform this operation...");
                    System.out.println(crmSystem.startProductionByClientId(clientId));
                    break;
                case 3:
                    productManagerChoice = 3;
                    System.out.println("Ok. Bye-bye!");
                    break;
                case 4:
                    break;
            }
        }

    }
}
