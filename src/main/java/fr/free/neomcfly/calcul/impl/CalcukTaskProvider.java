package fr.free.neomcfly.calcul.impl;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import lombok.extern.slf4j.Slf4j;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import com.techcielo.spring4.jms.provider.Product;

@Slf4j
@Service("msgProviderSvc")
public class CalcukTaskProvider {

    @Resource(name = "jmstemplate")
    private JmsTemplate jmsTemplate;

    public void sendMessages() {
        for (int i = 1; i <= 50; i++) {
            sendMessage("Tache:" + i);
        }
    }

    public void sendMessage(String string) {
        final Product prod = new Product();

        prod.setName(string);

        log.info("Send " + prod.getName());

        jmsTemplate.send(new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                TextMessage msg = session.createTextMessage(prod.getName());
                return msg;
            }
        });
    }
}