package org.softuni.nuggets.messaging;

import org.softuni.nuggets.services.NuggetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class ExtractNuggetsSubscriber {

    private final NuggetService nuggetService;
    private final JmsTemplate jmsTemplate;

    @Autowired
    public ExtractNuggetsSubscriber(NuggetService nuggetService, JmsTemplate jmsTemplate) {
        this.nuggetService = nuggetService;
        this.jmsTemplate = jmsTemplate;
    }

    @JmsListener(destination = "register-user")
    public void onRegister(Message message) throws JMSException {
//        if(message instanceof ActiveMQMapMessage) {
            MapMessage mappedMessage = (MapMessage)message;
            Map<String, Object> messageMap = new HashMap<>();
            messageMap.put("username", mappedMessage.getObject("username"));
            List<String> preferences = (List<String>) mappedMessage.getObject("preferences");
            List<String> nuggets = nuggetService.findNuggets(preferences);
            messageMap.put("preferences", nuggets != null && !nuggets.isEmpty() ? nuggets.stream().collect(Collectors.joining(",")) : "" );

            this.jmsTemplate.convertAndSend("nuggets-result", messageMap);
//        }
    }
}
