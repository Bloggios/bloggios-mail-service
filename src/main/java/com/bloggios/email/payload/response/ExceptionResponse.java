package com.bloggios.email.payload.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

/**
 * Owner - Rohit Parihar and Bloggios
 * Author - rohit
 * Project - bloggios-email-service
 * Package - com.bloggios.email.payload.response
 * Created_on - May 01 - 2024
 * Created_at - 16:37
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExceptionResponse {

    private String message;
    private String code;
    private String field;
    private String type;
    private String status;
}
