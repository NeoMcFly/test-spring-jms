package neomcfly.calcul.impl;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CalculTaskConsumerListener implements MessageListener {

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

	public void onMessage(Message message) {
		try {
			this.readMessage(message);
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
}