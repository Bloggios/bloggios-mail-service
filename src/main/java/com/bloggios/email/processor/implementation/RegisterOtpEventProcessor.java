/*
 * Copyright © 2023-2024 Rohit Parihar and Bloggios
 * All rights reserved.
 * This software is the property of Rohit Parihar and is protected by copyright law.
 * The software, including its source code, documentation, and associated files, may not be used, copied, modified, distributed, or sublicensed without the express written consent of Rohit Parihar.
 * For licensing and usage inquiries, please contact Rohit Parihar at rohitparih@gmail.com, or you can also contact support@bloggios.com.
 * This software is provided as-is, and no warranties or guarantees are made regarding its fitness for any particular purpose or compatibility with any specific technology.
 * For license information and terms of use, please refer to the accompanying LICENSE file or visit http://www.apache.org/licenses/LICENSE-2.0.
 * Unauthorized use of this software may result in legal action and liability for damages.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.bloggios.email.processor.implementation;

import com.bloggios.email.constants.BeanNameConstants;
import com.bloggios.email.dao.implementation.pgabstractdao.MailHistoryEntityDao;
import com.bloggios.email.entity.MailHistoryEntity;
import com.bloggios.email.enums.DaoStatus;
import com.bloggios.email.javamail.implementation.RegistrationOtpMailSending;
import com.bloggios.email.payload.event.RegisterOtpMailEvent;
import com.bloggios.email.processor.Process;
import com.bloggios.email.transformer.implementation.transform.RegisterOtpToMailHistoryTransformer;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;

/**
 * Owner - Rohit Parihar
 * Author - rohit
 * Project - bloggios-notification-service
 * Package - com.bloggios.notification.service.processor.implementation
 * Created_on - 19 December-2023
 * Created_at - 19 : 46
 */

@Component(BeanNameConstants.REGISTER_OTP_EVENT_PROCESSOR)
public class RegisterOtpEventProcessor implements Process<RegisterOtpMailEvent> {

    private static final Logger logger = LoggerFactory.getLogger(RegisterOtpEventProcessor.class);

    private final RegistrationOtpMailSending registrationOtpMailSending;
    private final RegisterOtpToMailHistoryTransformer registerOtpToMailHistoryTransformer;
    private final MailHistoryEntityDao mailHistoryEntityDao;

    public RegisterOtpEventProcessor(
            RegistrationOtpMailSending registrationOtpMailSending,
            RegisterOtpToMailHistoryTransformer registerOtpToMailHistoryTransformer,
            MailHistoryEntityDao mailHistoryEntityDao
    ) {
        this.registrationOtpMailSending = registrationOtpMailSending;
        this.registerOtpToMailHistoryTransformer = registerOtpToMailHistoryTransformer;
        this.mailHistoryEntityDao = mailHistoryEntityDao;
    }

    @Override
    @SneakyThrows(value = MessagingException.class)
    @Async(BeanNameConstants.ASYNC_TASK_INTERNAL_POOL)
    public void process(RegisterOtpMailEvent registerOtpMailEvent) {
        long startTime = System.currentTimeMillis();
        registrationOtpMailSending.sendMail(registerOtpMailEvent);
        MailHistoryEntity transform = registerOtpToMailHistoryTransformer.transform(registerOtpMailEvent);
        mailHistoryEntityDao.initOperation(DaoStatus.CREATE, transform);
        logger.info("Execution complete for Register OTP mail sending in {}ms", System.currentTimeMillis() - startTime);
    }
}
