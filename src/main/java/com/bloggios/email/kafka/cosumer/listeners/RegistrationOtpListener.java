package com.bloggios.email.kafka.cosumer.listeners;

import com.bloggios.email.constants.InternalErrorCodes;
import com.bloggios.email.constants.ServiceConstants;
import com.bloggios.email.exceptions.ExceptionProvider;
import com.bloggios.email.exceptions.payloads.InitializationException;
import com.bloggios.email.payload.IncomingMessage;
import com.bloggios.email.payload.event.RegisterOtpMailEvent;
import com.bloggios.email.processor.Process;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.MDC;
import org.springframework.kafka.listener.ConsumerAwareMessageListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

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

    private static final ObjectMapper mapper = new ObjectMapper();
    private final Process<RegisterOtpMailEvent> registerOtpMailEventProcess;

    public RegistrationOtpListener(
            Process<RegisterOtpMailEvent> registerOtpMailEventProcess
    ) {
        this.registerOtpMailEventProcess = registerOtpMailEventProcess;
    }

    @Override
    public void onMessage(ConsumerRecord<String, IncomingMessage> consumerRecord, Consumer<?, ?> consumer) {
        try {
            RegisterOtpMailEvent registerOtpMailEvent = mapper.readValue(mapper.writeValueAsBytes(consumerRecord.value().getMessageData().getData()), RegisterOtpMailEvent.class);
            String breadcrumbId = mapper.readValue(mapper.writeValueAsBytes(consumerRecord.value().getMessageData().getBreadcrumbId()), String.class);
            MDC.put(ServiceConstants.BREADCRUMB_ID, breadcrumbId);
            registerOtpMailEventProcess.process(registerOtpMailEvent);
        } catch (IOException ignored) {
            throw new InitializationException(InternalErrorCodes.JSON_DESERIALIZATION);
        } finally {
            MDC.remove(ServiceConstants.BREADCRUMB_ID);
            consumer.commitAsync();
        }
    }
}
