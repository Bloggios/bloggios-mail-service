package com.bloggios.email.kafka.cosumer;

import com.bloggios.email.constants.BeanNameConstants;
import com.bloggios.email.payload.IncomingMessage;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Owner - Rohit Parihar and Bloggios
 * Author - rohit
 * Project - bloggios-email-service
 * Package - com.bloggios.email.kafka.cosumer
 * Created_on - May 20 - 2024
 * Created_at - 14:03
 */

@Component(BeanNameConstants.INCOMING_MESSAGE_FACTORY_COMPONENT)
@DependsOn(BeanNameConstants.MANAGEMENT_CONSUMER_FACTORY)
public class IncomingMessageFactory extends ConcurrentKafkaListenerContainerFactory<String, IncomingMessage> {

    private final ConsumerFactory<String, IncomingMessage> consumerFactory;

    public IncomingMessageFactory(
            @Qualifier(BeanNameConstants.MANAGEMENT_CONSUMER_FACTORY) ConsumerFactory<String, IncomingMessage> consumerFactory
    ) {
        this.consumerFactory = consumerFactory;
    }

    @PostConstruct
    public void configure() {
        setConsumerFactory(consumerFactory);
        getContainerProperties()
                .setAckMode(ContainerProperties.AckMode.MANUAL);
    }
}
