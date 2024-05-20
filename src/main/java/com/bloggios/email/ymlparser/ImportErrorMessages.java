package com.bloggios.email.ymlparser;

import com.bloggios.email.constants.BeanNameConstants;
import com.bloggios.email.constants.InternalErrorCodes;
import com.bloggios.email.exceptions.payloads.InternalException;
import com.thoughtworks.xstream.InitializationException;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Owner - Rohit Parihar and Bloggios
 * Author - rohit
 * Project - bloggios-email-service
 * Package - com.bloggios.email.ymlparser
 * Created_on - May 01 - 2024
 * Created_at - 16:16
 */

@Configuration
public class ImportErrorMessages {

    @SneakyThrows(value = {IOException.class, InitializationException.class})
    @Bean(BeanNameConstants.ERROR_CODES_BEAN)
    public Properties errorMessages() {
        File file = ResourceUtils.getFile("classpath:error-messages.properties");
        InputStream in = new FileInputStream(file);
        Properties properties = new Properties();
        try {
            properties.load(in);
        } catch (IOException exception) {
            throw new InternalException(InternalErrorCodes.EXCEPTION_CODES);
        } finally {
            in.close();
        }
        return properties;
    }
}
