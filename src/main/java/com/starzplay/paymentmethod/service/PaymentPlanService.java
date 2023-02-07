package com.starzplay.paymentmethod.service;

import org.springframework.http.ResponseEntity;

/**
 * @author: Nabeel Tariq Bhatti
 * @created: 2023/02/07
 */
public interface PaymentPlanService {
    ResponseEntity<? extends Object> getByPaymentMethodByPaymentPlanId(String id);
}
