package spittr.dumyservice.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import spittr.domain.model.Reference;
import spittr.dumyservice.config.JmsConfig;

import java.util.concurrent.ThreadLocalRandom;

@Slf4j
@Component
public class MessageListener {

    private JmsConfig jmsConfig;
    private String referenceUrl;
    private MessageSender messageSender;

    public MessageListener(JmsConfig jmsConfig,
                           @Value("${spittr.reference-url}") String referenceUrl,
                           MessageSender messageSender) {
        this.jmsConfig = jmsConfig;
        this.referenceUrl = referenceUrl;
        this.messageSender = messageSender;
    }

    @JmsListener(destination = "#{@jmsConfig.frontDummyQueue}")
    public void listen(Reference reference){
        int randomNum = ThreadLocalRandom.current().nextInt(0, 10 + 1);
        log.info("Got new reference [{}]", reference);
        reference.setRandom(randomNum);
        log.info("A random value [{}] added for reference", reference.getRandom());
        messageSender.sendUpdatedReference(reference);
    }

}
