package org.softuni.nuggets.messaging;

import org.softuni.nuggets.entities.User;
import org.softuni.nuggets.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.transaction.Transactional;

@Component
public class UserMessageListener {
    private final UserRepository userRepository;
    private final JmsTemplate jmsTemplate;

    @Autowired
    public UserMessageListener(UserRepository userRepository, JmsTemplate jmsTemplate) {
        this.userRepository = userRepository;
        this.jmsTemplate = jmsTemplate;
    }

    @JmsListener(destination = "nuggets-result")
    @Transactional
    public void onRegister(Message message) throws JMSException {
//        if(message instanceof ActiveMQMapMessage) {
            MapMessage mappedMessage = (MapMessage)message;
            String username = mappedMessage.getString("username");
            String preferences = mappedMessage.getString("preferences");
            User user = this.userRepository.findByUsername(username);
            user.setPreferences(preferences);
            this.userRepository.saveAndFlush(user);
//        }
    }
}
