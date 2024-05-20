package com.bloggios.email.exceptions.payloads;

import com.bloggios.email.exceptions.ExceptionProvider;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * Owner - Rohit Parihar and Bloggios
 * Author - rohit
 * Project - bloggios-email-service
 * Package - com.bloggios.email.exceptions.payloads
 * Created_on - May 20 - 2024
 * Created_at - 12:59
 */

@Getter
@EqualsAndHashCode(callSuper = true)
public class InitializationException extends ExceptionProvider {

    public InitializationException(String code) {
        super(code);
    }
}
