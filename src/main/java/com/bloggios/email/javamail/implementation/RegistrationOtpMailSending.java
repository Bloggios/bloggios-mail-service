package com.bloggios.email.javamail.implementation;

import com.bloggios.email.constants.EnvironmentConstants;
import com.bloggios.email.javamail.GmailSending;
import com.bloggios.email.payload.event.RegisterOtpMailEvent;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Objects;

/**
 * Owner - Rohit Parihar
 * Author - rohit
 * Project - bloggios-notification-service
 * Package - com.bloggios.notification.service.javamail.implementation
 * Created_on - 19 December-2023
 * Created_at - 22 : 20
 */

@Component
public class RegistrationOtpMailSending extends GmailSending<RegisterOtpMailEvent> {

    private final TemplateEngine templateEngine;
    private final Environment environment;

    protected RegistrationOtpMailSending(
            JavaMailSender javaMailSender,
            TemplateEngine templateEngine,
            Environment environment
    ) {
        super(javaMailSender);
        this.templateEngine = templateEngine;
        this.environment = environment;
    }

    private String getContext(RegisterOtpMailEvent registerOtpMailEvent) {
        Context context = new Context();
        context.setVariable("otpPayload", registerOtpMailEvent);
        return templateEngine.process("RegistrationOtp", context);
    }

    @Override
    public void mailingData(MimeMessage message, RegisterOtpMailEvent request) throws MessagingException {
        String context = getContext(request);
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(Objects.requireNonNull(environment.getProperty(EnvironmentConstants.GOOGLE_MAIL_USERNAME)));
        helper.setSubject("Bloggios | Registration OTP");
        helper.setText(context, true);
        helper.setTo(request.getEmail());
    }
}
