package com.bloggios.email.dao.implementation.pgabstractdao;

import com.bloggios.email.dao.PgAbstractDao;
import com.bloggios.email.dao.repository.pgrepository.MailHistoryRepository;
import com.bloggios.email.entity.MailHistoryEntity;
import org.springframework.stereotype.Component;

/**
 * Owner - Rohit Parihar and Bloggios
 * Author - rohit
 * Project - bloggios-email-service
 * Package - com.bloggios.email.dao.implementation.pgabstractdao
 * Created_on - May 06 - 2024
 * Created_at - 20:03
 */

@Component
public class MailHistoryEntityDao extends PgAbstractDao<MailHistoryEntity, MailHistoryRepository> {

    public MailHistoryEntityDao(MailHistoryRepository repository) {
        super(repository);
    }
}
