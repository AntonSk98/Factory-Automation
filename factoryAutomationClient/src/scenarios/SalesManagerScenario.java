package scenarios;

import com.production.service.CRMSystem;
import com.production.service.CRMSystemRemote;
import utils.BeanGenerator;

import javax.naming.NamingException;
import java.util.Scanner;

public class SalesManagerScenario implements Scenario {

    CRMSystemRemote crmSystem = new BeanGenerator<CRMSystemRemote>().generateBean(CRMSystem.class, CRMSystemRemote.class);


    public SalesManagerScenario() throws NamingException {
    }

    @Override
    public void playRole(Scanner userInput) {
        int salesManagerChoice = 0;
        System.out.println("Welcome sales manager!");
        System.out.println("There are all your available operations...");
        while (salesManagerChoice != 8) {
            System.out.println("1 - View all approved customer ids");
            System.out.println("2 - View all unapproved customer ids");
            System.out.println("3 - Approve customer by id");
            System.out.println("4 - Get all customers");
            System.out.println("5 - Get user ids that are waiting for their products");
            System.out.println("6 - Create invoice by customer id");
            System.out.println("7 - Ship order by customer id");
            System.out.println("8 - Go back");
            salesManagerChoice = userInput.nextInt();
            switch (salesManagerChoice) {
                case 1:
                    performViewAllApprovedUserIdsBranch();
                    break;
                case 2:
                    performViewAllUnapprovedUserIdsBranch();
                    break;
                case 3:
                    performApproveUserByIdsBranch(userInput);
                    break;
                case 4:
                    performGetAllCustomersBranch();
                    break;
                case 5:
                    performWaitingUserIdsBranch();
                    break;
                case 6:
                    performCreateInvoiceBranch(userInput);
                    break;
                case 7:
                    performShipProductBranch(userInput);
                case 8:
                    System.out.println("Ok. Bye-Bye!");
                    break;
            }
        }
    }

    private void performShipProductBranch(Scanner userInput) {
        System.out.println("Please enter the user id whom we should ship the product");
        int id = userInput.nextInt();
        System.out.println(crmSystem.shipProductByClientId(id));
    }

    private void performWaitingUserIdsBranch() {
        System.out.println("There are client ids that are waiting to receive their order....");
        System.out.println(crmSystem.getClientIdsWaitingForShipment());
    }

    private void performCreateInvoiceBranch(Scanner userInput) {
        System.out.println("Please enter the user id whom we should send an invoice");
        int id = userInput.nextInt();
        System.out.println(crmSystem.sendInvoiceByClientId(id));
    }

    private void performGetAllCustomersBranch() {
        System.out.println("Showing all user...");
        crmSystem.getAllCustomers().forEach(System.out::println);
    }

    private void performApproveUserByIdsBranch(Scanner userInput) {
        System.out.println("Please enter the user id whose user you would like to approve!");
        int id = userInput.nextInt();
        System.out.println(crmSystem.approveCustomerById(id).getValue());
    }

    private void performViewAllUnapprovedUserIdsBranch() {
        System.out.println("Please look at all user ids that are waiting to be approved!");
        System.out.println(crmSystem.getAllNotApprovedCustomersIds());
    }

    private void performViewAllApprovedUserIdsBranch() {
        System.out.println("Please look at all user ids that are already approved!");
        System.out.println(crmSystem.getAllApprovedCustomersId());
    }
}
