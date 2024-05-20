package com.bloggios.email.kafka.cosumer.listeners;

import com.bloggios.email.payload.IncomingMessage;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.listener.ConsumerAwareMessageListener;
import org.springframework.stereotype.Component;

/**
 * Owner - Rohit Parihar and Bloggios
 * Author - rohit
 * Project - bloggios-email-service
 * Package - com.bloggios.email.kafka.cosumer.listeners
 * Created_on - May 20 - 2024
 * Created_at - 14:28
 */

@Component
public class RegistrationOtpListener implements ConsumerAwareMessageListener<String, IncomingMessage> {

    @Override
    public void onMessage(ConsumerRecord<String, IncomingMessage> consumerRecord, Consumer<?, ?> consumer) {
        System.err.println(consumerRecord.value());
    }
}
