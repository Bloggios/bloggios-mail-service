package com.bloggios.email.transformer;

/**
 * Owner - Rohit Parihar and Bloggios
 * Author - rohit
 * Project - bloggios-email-service
 * Package - com.bloggios.email.transformer
 * Created_on - May 06 - 2024
 * Created_at - 20:05
 */

@FunctionalInterface
public interface Transform<A, B> {

    A transform(B b);
}
