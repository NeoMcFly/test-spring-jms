package fr.free.neomcfly.calcul.impl;

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
public class CalculTaskConsumerListener {

    @Resource(name = "jmstemplate")
    private JmsTemplate jmsTemplate;

    @JmsListener(destination = "springtest")
    public void readMessage(Message message) throws JMSException {

        if (message instanceof TextMessage) {
            TextMessage tm = (TextMessage) message;
            String msg = tm.getText();

            long millis = (long) (1000 * (5 + (Math.random() * 10)));

            log.info("> Read Message " + msg + " wait(" + millis + ")");

            try {
                Thread.sleep(millis);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            log.info("< Read Message " + msg);

        }
    }
}