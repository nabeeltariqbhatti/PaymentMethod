package com.starzplay.paymentmethod.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.Date;

/**
 * @author: Nabeel Tariq Bhatti
 * @created: 2023/02/07
 */
@Getter
@Setter
@Builder
public class GenericExceptionResponse {
    private String message;
    private Date time;
    private int code;
}
