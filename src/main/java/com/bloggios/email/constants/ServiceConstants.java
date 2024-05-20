package com.bloggios.email.constants;

import lombok.experimental.UtilityClass;

/**
 * Owner - Rohit Parihar and Bloggios
 * Author - rohit
 * Project - bloggios-email-service
 * Package - com.bloggios.email.constants
 * Created_on - May 20 - 2024
 * Created_at - 12:51
 */

@UtilityClass
public class ServiceConstants {

    public static final String LISTENER_TOPIC = "#{__listener.topics}";
    public static final String LISTENER_GROUP_ID = "#{__listener.groupId}";
    public static final String CONTAINER_FACTORY_BEAN_NAME = "#{__listener.containerFactoryBeanName}";
    public static final String TOPICS = "Topics";
    public static final String SERVICE_NAME = "ServiceName";
    public static final String SERVICE_CONTAINER_FACTORY_NAME = "ServiceContainerFactoryName";
    public static final String RANDOM_UUID = "randomUUID";
    public static final String BREADCRUMB_ID = "breadcrumbId";
}
