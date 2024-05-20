package com.bloggios.email.entity;

import com.bloggios.email.constants.ServiceConstants;
import com.bloggios.email.enums.MailFor;
import com.bloggios.email.enums.MailProvider;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Owner - Rohit Parihar and Bloggios
 * Author - rohit
 * Project - bloggios-email-service
 * Package - com.bloggios.email.entity
 * Created_on - May 06 - 2024
 * Created_at - 19:49
 */

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "mail_history")
public class MailHistoryEntity {

    @Id
    @GeneratedValue(generator = ServiceConstants.RANDOM_UUID)
    @GenericGenerator(name = ServiceConstants.RANDOM_UUID, strategy = "org.hibernate.id.UUIDGenerator")
    private String mailHistoryId;

    private String mailTo;
    private String mailContent;
    private MailProvider mailProvider;
    private MailFor mailFor;
    private Date dateSent;
    private String apiVersion;
}
