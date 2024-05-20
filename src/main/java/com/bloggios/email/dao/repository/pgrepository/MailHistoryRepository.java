package com.bloggios.email.dao.repository.pgrepository;

import com.bloggios.email.entity.MailHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Owner - Rohit Parihar and Bloggios
 * Author - rohit
 * Project - bloggios-email-service
 * Package - com.bloggios.email.dao.repository.pgrepository
 * Created_on - May 06 - 2024
 * Created_at - 20:02
 */

public interface MailHistoryRepository extends JpaRepository<MailHistoryEntity, String> {
}
