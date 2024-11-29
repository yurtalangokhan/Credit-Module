package com.inghub.core.exceptionHandling;

import lombok.Data;

/**
 * @author gyurtalan
 * @version 1.0
 */
@Data
public class ErrorResponse {

    private String status;
    private String message;
    private Long timeStamp;
}
