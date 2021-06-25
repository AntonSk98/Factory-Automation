package scenarios;

import com.production.entity.orderManagement.*;
import com.production.model.SimpleResponse;
import com.production.model.UserSessionResponse;
import com.production.service.CRMSystem;
import com.production.service.CRMSystemRemote;
import org.apache.activemq.artemis.utils.json.JSONObject;
import utils.BeanGenerator;

import javax.naming.NamingException;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CustomerScenario implements Scenario {

    CRMSystemRemote crmSystem = new BeanGenerator<CRMSystemRemote>().generateBean(CRMSystem.class, CRMSystemRemote.class);

    private int registeredClientId;

    public CustomerScenario() throws NamingException {
        JSONObject jsonObject = new JSONObject();
    }

    @Override
    public void playRole(Scanner userInput) throws IOException {
        int customerChoice = 0;
        this.registeredClientId = 0;
        while (customerChoice != 3) {
            System.out.println("Welcome customer! \n");
//            System.out.println(crmSystem.test());
            if (registeredClientId == 0)
                System.err.println("In order to continue you should sign in...");

            System.out.println("1 - Sign up");
            System.out.println("2 - Sign in");
            System.out.println("3 - Order related questions");
            System.out.println("4 - Go back");

            customerChoice = userInput.nextInt();
            switch (customerChoice) {
                case 1:
                    performSignUpBranch(userInput);
                    break;
                case 2:
                    performSignInBranch(userInput);
                    break;
                case 3:
                    if (registeredClientId == 0) {
                        System.out.println("Please log in first to perform this operation....\n");
                        break;
                    }
                    performCreateOrderBranch(userInput);
                    break;
                case 4:
                    System.out.println("Ok. Bye-bye! \n");
                    customerChoice = 3;
                    break;
            }
        }
    }

    public void performSignUpBranch(Scanner userInput) {
        System.out.println("Great!");
        System.out.println("Please enter your username:");
        String username = userInput.next();
        System.out.println("Please enter password:");
        String password = userInput.next();
        System.out.println("Please enter a street:");
        String street = userInput.next();
        System.out.println("Please enter a city");
        String city = userInput.next();
        System.out.println("Please enter a country");
        String country = userInput.next();
        System.out.println("Please wait...");
        UserSessionResponse userSessionResponse = crmSystem.signUp(username, password, street, city, country);
        System.out.println(userSessionResponse.getValue());
        if (userSessionResponse.getIsOpSuccessful()) {
            System.out.println("You successfully registered. Your user id is: " + userSessionResponse.getUserId());
        }
    }

    public void performSignInBranch(Scanner userInput) {
        System.out.println("To log in provide the following credentials!...");

        System.out.println("Please enter your username:");
        String loggedUsername = userInput.next();
        System.out.println("Please enter your password:");
        String loggedPassword = userInput.next();
        System.out.println("Please wait...");
        UserSessionResponse userSessionResponse = crmSystem.signIn(loggedUsername, loggedPassword);
        System.out.println(userSessionResponse.getValue());
        if (!userSessionResponse.getIsOpSuccessful()) {
            System.out.println("Error occurred while registering...");
            return;
        }
        this.registeredClientId = userSessionResponse.getUserId();
        System.out.println(this.registeredClientId);
    }

    public void performCreateOrderBranch(Scanner userInput) {
        int userOrderChoice = 0;
        int amount;
        System.out.println("Thanks for choosing our service!...");
        System.out.println("Here you can ask us to do the following: ");
        List<Order> orders = new ArrayList<>();
        while (userOrderChoice != 7) {

            System.out.println(
                    "1 - Print Photo \n" +
                            "2 - Scan Photo \n" +
                            "3 - Print paper document \n" +
                            "4 - Scan paper document \n" +
                            "5 - Submit your order \n" +
                            "6 - View your order (only submitted!) \n" +
                            "7 - Go back"
            );

            System.out.println("\n");

            if (userOrderChoice != 0) {
                System.out.println("Anything else? :)");
            }

            userOrderChoice = userInput.nextInt();
            switch (userOrderChoice) {
                case 1:
                    amount = addSOrderQuantityHelper(userInput);
                    orders.add(new PrintPhotoOrder(amount, this.registeredClientId));
                    break;
                case 2:
                    amount = addSOrderQuantityHelper(userInput);
                    orders.add(new ScanPhotoOrder(amount, this.registeredClientId));
                    break;
                case 3:
                    amount = addSOrderQuantityHelper(userInput);
                    orders.add(new PrintDocumentOrder(amount, this.registeredClientId));
                    break;
                case 4:
                    amount = addSOrderQuantityHelper(userInput);
                    orders.add(new ScanDocumentOrder(amount, this.registeredClientId));
                    break;
                case 5:
                    SimpleResponse simpleResponse = crmSystem.submitOrder(this.registeredClientId, orders);
                    System.out.println(simpleResponse.getValue());
                    break;
                case 6:
                    System.out.println(crmSystem.getAllServicesByClientId(this.registeredClientId));
                    break;
                case 7:
                    userOrderChoice = 7;
                    break;
            }
        }
    }

    private int addSOrderQuantityHelper(Scanner userInput) {
        System.out.println("Print amount...");
        return userInput.nextInt();
    }
}
