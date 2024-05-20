package com.bloggios.email.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Owner - Rohit Parihar and Bloggios
 * Author - rohit
 * Project - bloggios-email-service
 * Package - com.bloggios.email.exceptions
 * Created_on - May 20 - 2024
 * Created_at - 12:58
 */

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ExceptionProvider extends RuntimeException {

    private final String code;
}
