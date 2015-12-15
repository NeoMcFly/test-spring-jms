package com.techcielo.spring4.jms.consumer;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

import lombok.extern.slf4j.Slf4j;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service("msgConsumerLst")
public class HelloMessageConsumerListener {

    @Resource(name = "jmstemplate")
    private JmsTemplate jmsTemplate;

    @JmsListener(destination = "springtest")
    public void readMessage(Message message) throws JMSException {

        System.out.println("Message listened");

        if (message instanceof TextMessage) {
            TextMessage tm = (TextMessage) message;
            String msg = tm.getText();

            System.out.println(msg);

        }

        System.out.println(message.getClass());
        System.out.println("Done");

    }
}