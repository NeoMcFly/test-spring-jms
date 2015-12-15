package com.techcielo.spring4.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.techcielo.spring4.jms.consumer.HelloMessageConsumer;
import com.techcielo.spring4.jms.consumer.HelloMessageConsumerListener;
import com.techcielo.spring4.jms.provider.HelloMessageProvider;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        // Build application context by reading spring-config.xml
        ApplicationContext ctx = new ClassPathXmlApplicationContext(
                new String[] { "spring-config.xml" });

        // Get an instance of ProductService class;
        HelloMessageProvider prdSvc = (HelloMessageProvider) ctx
                .getBean("msgProviderSvc");

        HelloMessageConsumer conSvc = (HelloMessageConsumer) ctx
                .getBean("msgConsumerSvc");

        HelloMessageConsumerListener conLst = (HelloMessageConsumerListener) ctx
                .getBean("msgConsumerLst");

        // Call getProduct method of ProductService

        prdSvc.sendMessage(1);

        Thread.sleep(1000 * 5);

        // try {
        // conSvc.readMessage();
        // } catch (JMSException e) {
        // e.printStackTrace();
        // }
    }

}