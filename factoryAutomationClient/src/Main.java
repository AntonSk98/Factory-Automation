import scenarios.CustomerScenario;
import scenarios.ProductionManagerScenario;
import scenarios.SalesManagerScenario;

import javax.naming.NamingException;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws NamingException, IOException {
        int userChoice = 0;
        Scanner userInput = new Scanner(System.in);

        while (userChoice != 4) {
            System.out.println("Welcome to FactoryAutomation!");
            System.out.println("To continue, please type in one of possible roles: ");
            System.out.println("1 - Customer");
            System.out.println("2 - Sales Manager");
            System.out.println("3 - Production Manager");
            System.out.println("4 - Exit");
            userChoice = userInput.nextInt();
            switch (userChoice) {
                case 1:
                    new CustomerScenario().playRole(userInput);
                    break;
                case 2:
                    new SalesManagerScenario().playRole(userInput);
                    break;
                case 3:
                    new ProductionManagerScenario().playRole(userInput);
                    break;
                case 4:
                    System.out.println("Thank you very much! Bye-bye...");
                    userChoice = 4;
                    break;
            }
        }

    }
}

