package fr.free.neomcfly.calcul;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainConsumer {

    public static void main(String[] args) throws InterruptedException {

        // Build application context by reading spring-config.xml
        ApplicationContext ctx = new ClassPathXmlApplicationContext(
                new String[] { "spring-config.xml" });

    }

}
