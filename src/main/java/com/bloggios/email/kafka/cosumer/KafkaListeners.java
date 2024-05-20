package com.bloggios.email.kafka.cosumer;

import com.bloggios.email.constants.BeanNameConstants;
import com.bloggios.email.constants.EnvironmentConstants;
import com.bloggios.email.kafka.cosumer.listeners.RegistrationOtpListener;
import com.bloggios.email.payload.IncomingMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * Owner - Rohit Parihar and Bloggios
 * Author - rohit
 * Project - bloggios-email-service
 * Package - com.bloggios.email.kafka.cosumer
 * Created_on - May 20 - 2024
 * Created_at - 14:14
 */

@Component
public class KafkaListeners {

    private final Environment environment;
    private final RegistrationOtpListener registrationOtpListener;

    public KafkaListeners(
            Environment environment,
            RegistrationOtpListener registrationOtpListener
    ) {
        this.environment = environment;
        this.registrationOtpListener = registrationOtpListener;
    }

    @Bean
    @DependsOn(BeanNameConstants.INCOMING_MESSAGE_FACTORY_COMPONENT)
    public InitListener<String, IncomingMessage> registrationOtpPayload() {
        Properties properties = getProperties(EnvironmentConstants.REGISTRATION_OTP_TOPIC);
        return new InitListener<>(properties, registrationOtpListener);
    }

    private Properties getProperties(String registrationOtpTopic) {
        Properties properties = new Properties();
        properties.put(InitListener.KAFKA_TOPIC, environment.getProperty(registrationOtpTopic));
        properties.put(InitListener.KAFKA_GROUP, environment.getProperty(EnvironmentConstants.KAFKA_GROUP_ID));
        properties.put(InitListener.FACTORY_NAME, BeanNameConstants.INCOMING_MESSAGE_FACTORY_COMPONENT);
        return properties;
    }
}
