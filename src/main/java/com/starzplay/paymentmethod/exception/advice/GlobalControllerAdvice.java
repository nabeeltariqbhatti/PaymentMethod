package com.starzplay.paymentmethod.exception.advice;

import com.starzplay.paymentmethod.exception.BaseExceptionClass;
import com.starzplay.paymentmethod.exception.GenericExceptionResponse;
import com.starzplay.paymentmethod.exception.InternalServerException;
import com.starzplay.paymentmethod.exception.PaymentResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

/**
 * @author: Nabeel Tariq Bhatti
 * @created: 2023/02/07
 */

@ControllerAdvice
public class GlobalControllerAdvice {


    @ExceptionHandler(value = PaymentResourceNotFoundException.class)
    public ResponseEntity<GenericExceptionResponse> responseResponseExceptionHandler(PaymentResourceNotFoundException exception){
        return new ResponseEntity<>(
                GenericExceptionResponse.builder().code(exception.getStatus().value()).time(new Date()).message(exception.getMessage()).build(),
         exception.getStatus());
    }

    @ExceptionHandler(value =InternalServerException.class )
    public ResponseEntity<GenericExceptionResponse> internalServerError(InternalServerException exception){
        return new ResponseEntity<>(
                GenericExceptionResponse.builder().code(exception.getStatus().value()).time(new Date()).message(exception.getMessage()).build(),
                exception.getStatus());
    }

}
