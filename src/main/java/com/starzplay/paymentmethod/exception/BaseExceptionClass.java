package com.starzplay.paymentmethod.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

/**
 * @author: Nabeel Tariq Bhatti
 * @created: 2023/02/07
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
public class BaseExceptionClass extends RuntimeException{
    private String message;
    private HttpStatus status = HttpStatus.NOT_FOUND;
}
