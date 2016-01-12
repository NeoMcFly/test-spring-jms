package com.techcielo.spring4.jms.provider;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

@Service("msgProviderSvc")
public class HelloMessageProvider {

    @Resource(name = "jmstemplate")
    private JmsTemplate jmsTemplate;

    public void sendMessage(int id) {
        final Product prod = new Product();

        prod.setName("toto " + id);

        System.out.println("Send");
        System.out.println(prod.getName());

        jmsTemplate.send(new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                TextMessage msg = session.createTextMessage(prod.getName());
                return msg;
            }
        });
    }
}