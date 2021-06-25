package scenarios;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Scanner;

public interface Scenario {
    void playRole(Scanner userInput) throws IOException;
}
