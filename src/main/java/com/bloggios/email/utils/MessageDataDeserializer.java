package com.bloggios.email.utils;

import com.bloggios.email.constants.InternalErrorCodes;
import com.bloggios.email.exceptions.payloads.InitializationException;
import com.bloggios.email.payload.IncomingMessageData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.StdConverter;

/**
 * Owner - Rohit Parihar and Bloggios
 * Author - rohit
 * Project - bloggios-email-service
 * Package - com.bloggios.email.utils
 * Created_on - May 20 - 2024
 * Created_at - 12:56
 */

public class MessageDataDeserializer extends StdConverter<String, IncomingMessageData> {

    private static final ObjectMapper mapper = new ObjectMapper();

    @Override
    public IncomingMessageData convert(String s) {
        try {
            IncomingMessageData messageData = mapper.readValue(s, IncomingMessageData.class);
            messageData.setData(mapper.readValue(messageData.getData().toString(), Object.class));
            messageData.setBreadcrumbId(messageData.getBreadcrumbId());
            return messageData;
        } catch (JsonProcessingException ignored) {
            throw new InitializationException(InternalErrorCodes.JSON_DESERIALIZATION);
        }
    }
}
