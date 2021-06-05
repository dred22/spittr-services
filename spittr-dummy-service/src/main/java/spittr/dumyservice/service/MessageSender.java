package spittr.dumyservice.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import spittr.domain.model.Reference;
import spittr.dumyservice.config.JmsConfig;


@Slf4j
@Service
public class MessageSender {
    private final JmsTemplate jmsTemplate;
    private final JmsConfig jmsConfig;

    public MessageSender(JmsTemplate jmsTemplate, JmsConfig jmsConfig) {
        this.jmsTemplate = jmsTemplate;
        this.jmsConfig = jmsConfig;
    }

    public void sendUpdatedReference(Reference reference){
        jmsTemplate.convertAndSend(jmsConfig.getDummyDbQueue(), reference);
        log.info("Updated reference [{}] pushed into DB queue", reference.getId());
    }
}


