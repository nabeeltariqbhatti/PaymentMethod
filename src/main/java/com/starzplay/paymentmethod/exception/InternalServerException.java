package com.starzplay.paymentmethod.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

/**
 * @author: Nabeel Tariq Bhatti
 * @created: 2023/02/07
 */

public class InternalServerException extends BaseExceptionClass{

    public InternalServerException(String message, HttpStatus status) {
        super(message, status);
    }
}
