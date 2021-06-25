package utils;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;

public class Context {

    private static InitialContext initialContext;

    private Context() {

    }
    public static InitialContext getContext() throws NamingException {
        if (initialContext == null) {
            Properties props = new Properties();
            props.put("java.naming.factory.url.pkgs","org.jboss.ejb.client.naming");
            initialContext = new InitialContext(props);
        }
        return initialContext;
    }
}
