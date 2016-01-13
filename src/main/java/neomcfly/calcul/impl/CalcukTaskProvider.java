package neomcfly.calcul.impl;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

@Slf4j
public class CalcukTaskProvider {

	@Getter
	@Setter
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
            public Message createMessage(Session session) throws JMSException {
                TextMessage msg = session.createTextMessage(prod.getName());
                return msg;
            }
        });
    }
}