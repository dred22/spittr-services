package spittr.dumyservice.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

/**
 * Created by jt on 2019-07-17.
 */
@Configuration
@Getter
public class JmsConfig {

    private final String frontDummyQueue;
    private final String dummyDbQueue;

    public JmsConfig( @Value("${front.dummy.queue:front-dummy}") String frontDummyQueue,
                      @Value("${dummy.db:dummy-db}") String dummyDbQueue) {
        this.frontDummyQueue = frontDummyQueue;
        this.dummyDbQueue = dummyDbQueue;
    }

    @Bean
    public MessageConverter messageConverter(){
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_type");
        return converter;
    }
}
