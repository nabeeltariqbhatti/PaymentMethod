package com.starzplay.paymentmethod.service;

import com.starzplay.paymentmethod.dto.PaymentMethodDto;
import org.springframework.http.ResponseEntity;

/**
 * @author: Nabeel Tariq Bhatti
 * @created: 2023/02/07
 */
public interface PaymentMethodService {
    ResponseEntity<? extends Object> getAllPaymentMethods();

    ResponseEntity<? extends Object> getByPaymentMethodName(String name);

    ResponseEntity<PaymentMethodDto> updatePaymentMethod(Long id, PaymentMethodDto paymentMethod);

    ResponseEntity<?> deletePaymentMethod(Long id);

    ResponseEntity<? extends Object> createPaymentMethod(PaymentMethodDto paymentMethodDto);

    ResponseEntity<? extends Object> getPaymentMethod(Long id);
}
