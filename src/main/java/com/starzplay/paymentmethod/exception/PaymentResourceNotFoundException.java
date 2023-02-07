package com.starzplay.paymentmethod.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

/**
 * @author: Nabeel Tariq Bhatti
 * @created: 2023/02/07
 */

public class PaymentResourceNotFoundException extends BaseExceptionClass{

    public PaymentResourceNotFoundException(String message, HttpStatus status) {
        super(message, status);
    }
}
