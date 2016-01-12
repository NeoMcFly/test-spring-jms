package neomcfly.calcul;

import java.util.Scanner;

import lombok.extern.slf4j.Slf4j;
import neomcfly.user.impl.PoolUser;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@Slf4j
public class Main {

    private static final Scanner keyboard = new Scanner(System.in);

    public static void main(String[] args) throws InterruptedException {

        // Build application context by reading spring-config.xml
        ApplicationContext ctx = new ClassPathXmlApplicationContext(
                new String[] { "spring-config.xml" });

        // 50 user simultanees qui lance un calcul toutes les 30 secondes
        // chaque user lance 5 calcul

        // Get an instance of ProductService class;
        PoolUser prdSvc = (PoolUser) ctx.getBean("poolUser");

        // Call getProduct method of ProductService
        log.info("Press any key");
        keyboard.nextLine();

        prdSvc.run();

    }

}