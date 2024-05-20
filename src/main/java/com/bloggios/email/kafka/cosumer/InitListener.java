package com.bloggios.email.kafka.cosumer;

import com.bloggios.email.constants.ServiceConstants;
import lombok.Getter;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.listener.ConsumerAwareMessageListener;

import java.util.Properties;

/**
 * Owner - Rohit Parihar and Bloggios
 * Author - rohit
 * Project - bloggios-email-service
 * Package - com.bloggios.email.kafka.cosumer
 * Created_on - May 20 - 2024
 * Created_at - 12:50
 */

public class InitListener<A, B> {

    @Getter
    private final String[] topics;

    @Getter
    private final String groupId;

    @Getter
    private final String containerFactoryBeanName;
    private final ConsumerAwareMessageListener<A, B> messageListener;
    public static final String KAFKA_TOPIC = ServiceConstants.TOPICS;
    public static final String KAFKA_GROUP = ServiceConstants.SERVICE_NAME;
    public static final String FACTORY_NAME = ServiceConstants.SERVICE_CONTAINER_FACTORY_NAME;

    public InitListener(Properties properties, ConsumerAwareMessageListener<A, B> messageListener){
        this.topics = properties.getProperty(KAFKA_TOPIC, "").split(",");
        this.groupId = properties.getProperty(KAFKA_GROUP, "");
        this.containerFactoryBeanName = properties.getProperty(FACTORY_NAME, "");
        this.messageListener = messageListener;
    }

    @KafkaListener(
            topics = {ServiceConstants.LISTENER_TOPIC},
            groupId = ServiceConstants.LISTENER_GROUP_ID,
            containerFactory = ServiceConstants.CONTAINER_FACTORY_BEAN_NAME
    )
    public void attachListener(ConsumerRecord<A, B> consumerRecord, Consumer<?, ?> consumer){
        this.messageListener.onMessage(consumerRecord, consumer);
    }
}
