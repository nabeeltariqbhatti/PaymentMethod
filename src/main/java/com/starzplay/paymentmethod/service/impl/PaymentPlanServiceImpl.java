package com.starzplay.paymentmethod.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.starzplay.paymentmethod.dto.PaymentMethodDto;
import com.starzplay.paymentmethod.exception.InternalServerException;
import com.starzplay.paymentmethod.exception.PaymentResourceNotFoundException;
import com.starzplay.paymentmethod.repo.PaymentMethodRepo;
import com.starzplay.paymentmethod.repo.PaymentPlanRepo;
import com.starzplay.paymentmethod.service.PaymentPlanService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * @author: Nabeel Tariq Bhatti
 * @created: 2023/02/07
 */

@Service
public class PaymentPlanServiceImpl implements PaymentPlanService {
    private final PaymentMethodRepo paymentMethodRepo;
    private final ObjectMapper objectMapper;

    private final PaymentPlanRepo paymentPlanRepo;
    public PaymentPlanServiceImpl(PaymentMethodRepo paymentMethodRepo, ObjectMapper objectMapper, PaymentPlanRepo paymentPlanRepo) {
        this.paymentMethodRepo = paymentMethodRepo;
        this.objectMapper = objectMapper;
        this.paymentPlanRepo = paymentPlanRepo;
    }

   @Override
    public ResponseEntity<? extends Object> getByPaymentMethodByPaymentPlanId(String id) {
        try{
            List<PaymentMethod> paymentMethodsByPlanId = paymentMethodRepo.getPaymentMethodsByPlanId(Long.valueOf(id));
            List<PaymentMethodDto> paymentMethodDtos = objectMapper.convertValue(paymentMethodsByPlanId, new TypeReference<List<PaymentMethodDto>>() {
            });
            return ResponseEntity.status(HttpStatus.OK).body(paymentMethodDtos);
        }catch (Exception exception){
            throw new InternalServerException("Error while fetching payment method", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
