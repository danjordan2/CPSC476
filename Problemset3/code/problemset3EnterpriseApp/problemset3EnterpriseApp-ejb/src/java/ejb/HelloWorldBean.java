package ejb;

import javax.ejb.Stateless;

@Stateless
public class HelloWorldBean implements HelloWorldBeanRemote {


    public String hello(String name) {
        return "Hello, " + name;
    }
}
