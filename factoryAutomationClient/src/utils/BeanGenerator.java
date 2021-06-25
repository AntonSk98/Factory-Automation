package utils;

import javax.naming.NamingException;

public class BeanGenerator<T> {

    public T generateBean(Class<? extends T> bean, Class<T> interfaceBean) throws NamingException {

        String appName = "";
        String moduleName = "b_management-1.0-SNAPSHOT";
        String distinctName = "";

        String name = "ejb:" + appName + "/" + moduleName + "/" +  distinctName    + "/" + bean.getSimpleName() + "!" + interfaceBean.getName();
        System.out.println(name);
        return (T) Context.getContext().lookup(name);
    }
}

