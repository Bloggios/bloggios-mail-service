package com.bloggios.email.constants;

import lombok.experimental.UtilityClass;

/**
 * Owner - Rohit Parihar and Bloggios
 * Author - rohit
 * Project - bloggios-email-service
 * Package - com.bloggios.email.constants
 * Created_on - May 20 - 2024
 * Created_at - 14:25
 */

@UtilityClass
public class EnvironmentConstants {

    public static final String KAFKA_GROUP_ID = "mail-service.kafka.group-id";
    public static final String REGISTRATION_OTP_TOPIC = "mail-service.kafka.consumer.topics.registration-otp";
}
