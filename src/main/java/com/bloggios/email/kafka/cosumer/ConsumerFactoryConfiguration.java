package com.bloggios.email.kafka.cosumer;

import com.bloggios.email.constants.BeanNameConstants;
import com.bloggios.email.payload.IncomingMessage;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.stereotype.Component;

/**
 * Owner - Rohit Parihar and Bloggios
 * Author - rohit
 * Project - bloggios-email-service
 * Package - com.bloggios.email.kafka.cosumer
 * Created_on - May 20 - 2024
 * Created_at - 14:05
 */

@Component
public class ConsumerFactoryConfiguration {

    private final KafkaProperties kafkaProperties;

    public ConsumerFactoryConfiguration(
            KafkaProperties kafkaProperties
    ) {
        this.kafkaProperties = kafkaProperties;
    }

    @Bean(BeanNameConstants.MANAGEMENT_CONSUMER_FACTORY)
    public ConsumerFactory<String, IncomingMessage> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(
                kafkaProperties.buildConsumerProperties(),
                new StringDeserializer(),
                new JsonDeserializer<>(IncomingMessage.class)
        );
    }
}
