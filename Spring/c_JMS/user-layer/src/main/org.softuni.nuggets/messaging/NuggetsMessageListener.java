package org.softuni.nuggets.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;

@Component
public class NuggetsMessageListener {

    private final JmsTemplate jmsTemplate;
    private String[] nuggets;

    @Autowired
    public NuggetsMessageListener(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void notifyForNuggets() throws JMSException {
        this.jmsTemplate.convertAndSend("get-nuggets");
    }

    @JmsListener(destination = "send-all-nuggets")
    public void receiveNuggets(Message<String> message) {
            this.nuggets = message.getPayload().split(",");

    }

    public String[] getNuggets() {
        return nuggets;
    }
}
