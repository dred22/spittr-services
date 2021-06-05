package spittr.web.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import spittr.domain.model.Reference;
import spittr.web.config.JmsConfig;


@Slf4j
@Service
public class MessageSender {
    private final JmsTemplate jmsTemplate;
    private final JmsConfig jmsConfig;

    public MessageSender(JmsTemplate jmsTemplate, JmsConfig jmsConfig) {
        this.jmsTemplate = jmsTemplate;
        this.jmsConfig = jmsConfig;
    }

    public void sendNewReference(Reference reference){
        jmsTemplate.convertAndSend(jmsConfig.getFrontDummyQueue(), reference);
        log.info("Massage pushed for new reference [{}]", reference.getId());
    }
}


