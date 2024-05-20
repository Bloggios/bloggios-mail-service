package com.bloggios.email.transformer.implementation.transform;

import com.bloggios.email.constants.EnvironmentConstants;
import com.bloggios.email.entity.MailHistoryEntity;
import com.bloggios.email.enums.MailFor;
import com.bloggios.email.enums.MailProvider;
import com.bloggios.email.payload.event.RegisterOtpMailEvent;
import com.bloggios.email.transformer.Transform;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Date;

import static com.bloggios.email.constants.EnvironmentConstants.APPLICATION_VERSION;

/**
 * Owner - Rohit Parihar and Bloggios
 * Author - rohit
 * Project - bloggios-email-service
 * Package - com.bloggios.email.transformer.implementation.transform
 * Created_on - May 06 - 2024
 * Created_at - 20:08
 */

@Component
public class RegisterOtpToMailHistoryTransformer implements Transform<MailHistoryEntity, RegisterOtpMailEvent> {

    private final Environment environment;

    public RegisterOtpToMailHistoryTransformer(
            Environment environment
    ) {
        this.environment = environment;
    }

    @Override
    public MailHistoryEntity transform(RegisterOtpMailEvent request) {
        return MailHistoryEntity
                .builder()
                .mailTo(request.getEmail())
                .mailContent(request.getOtp())
                .mailProvider(MailProvider.GMAIL)
                .mailFor(MailFor.REGISTER_OTP)
                .dateSent(new Date())
                .apiVersion(environment.getProperty(EnvironmentConstants.APPLICATION_VERSION))
                .build();
    }
}
