package com.bloggios.email.configuration;

import com.bloggios.email.constants.BeanNameConstants;
import com.bloggios.email.constants.EnvironmentConstants;
import com.bloggios.email.properties.MailProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Objects;

/**
 * Owner - Rohit Parihar and Bloggios
 * Author - rohit
 * Project - bloggios-email-service
 * Package - com.bloggios.email.configuration
 * Created_on - May 05 - 2024
 * Created_at - 16:17
 */

@Configuration
public class MailConfig {

    private final Environment environment;

    public MailConfig(
            Environment environment
    ) {
        this.environment = environment;
    }

    @Bean(BeanNameConstants.GOOGLE_MAIL_SENDER_BEAN)
    public JavaMailSender getGoogleMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(environment.getProperty(EnvironmentConstants.GOOGLE_MAIL_HOST));
        mailSender.setPort(Integer.parseInt(Objects.requireNonNull(environment.getProperty(EnvironmentConstants.GOOGLE_MAIL_PORT))));
        mailSender.setUsername(environment.getProperty(EnvironmentConstants.GOOGLE_MAIL_USERNAME));
        mailSender.setPassword(environment.getProperty(EnvironmentConstants.GOOGLE_MAIL_PASSWORD));
        MailProperties.mailProperties(mailSender);
        return mailSender;
    }
}
