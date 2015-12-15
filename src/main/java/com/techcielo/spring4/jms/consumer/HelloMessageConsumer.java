package com.techcielo.spring4.jms.consumer;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service("msgConsumerSvc")
public class HelloMessageConsumer {

    @Resource(name = "jmstemplate")
    private JmsTemplate jmsTemplate;

    public void readMessage() throws JMSException {

        System.out.println("Reading message");

        Message msg = jmsTemplate.receive();

        msg.acknowledge();
        msg.getJMSDestination();
        if (msg instanceof TextMessage) {
            TextMessage txtmsg = (TextMessage) msg;
            System.out.println(txtmsg.getText());

        }

        System.out.println(msg.getClass());
        System.out.println("Done");

    }
}