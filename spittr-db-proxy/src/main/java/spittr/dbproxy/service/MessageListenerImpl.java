package spittr.dbproxy.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import spittr.data.ReferenceRepository;
import spittr.dbproxy.config.JmsConfig;
import spittr.dbproxy.util.mapper.ReferenceMapper;
import spittr.domain.model.Reference;

@Slf4j
@Component
public class MessageListenerImpl {

    private JmsConfig jmsConfig;
    private ReferenceRepository referenceRepository;
    private ReferenceMapper referenceMapper;

    public MessageListenerImpl(JmsConfig jmsConfig, ReferenceRepository referenceRepository, ReferenceMapper referenceMapper) {
        this.jmsConfig = jmsConfig;
        this.referenceRepository = referenceRepository;
        this.referenceMapper = referenceMapper;
    }

    @JmsListener(destination = "#{@jmsConfig.dummyDbQueue}")
    public void listen(Reference reference){
        log.info("Got a reference to update [{}]", reference);
        referenceRepository.save(referenceMapper.referenceToReferenceEntity(reference));
        log.info("The reference updated");
    }

}
