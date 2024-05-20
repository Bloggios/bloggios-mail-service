package com.bloggios.email.service;

import com.bloggios.email.payload.event.RegisterOtpMailEvent;
import com.bloggios.email.payload.response.ApplicationResponse;

/**
 * Owner - Rohit Parihar and Bloggios
 * Author - rohit
 * Project - bloggios-email-service
 * Package - com.bloggios.email.service
 * Created_on - May 05 - 2024
 * Created_at - 16:44
 */

public interface MailSendingService {

    ApplicationResponse sendRegisterOtpMail(RegisterOtpMailEvent request);
}
