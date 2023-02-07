package com.starzplay.paymentmethod.service.impl;

import com.starzplay.paymentmethod.dto.PaymentMethodDto;
import com.starzplay.paymentmethod.dto.PaymentPlanDto;
import com.starzplay.paymentmethod.exception.InternalServerException;
import com.starzplay.paymentmethod.exception.PaymentResourceNotFoundException;
import com.starzplay.paymentmethod.repo.PaymentMethodRepo;
import com.starzplay.paymentmethod.repo.PaymentPlanRepo;
import com.starzplay.paymentmethod.service.PaymentPlanService;
import org.springframework.beans.BeanUtils;
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

    private final PaymentPlanRepo paymentPlanRepo;
    public PaymentPlanServiceImpl(PaymentMethodRepo paymentMethodRepo, PaymentPlanRepo paymentPlanRepo) {
        this.paymentMethodRepo = paymentMethodRepo;
        this.paymentPlanRepo = paymentPlanRepo;
    }

    @Override
    public ResponseEntity<? extends Object> getByPaymentMethodByPaymentPlanId(String id) {
        List<PaymentMethodDto> paymentMethodDtos = new LinkedList<>();
        try{
            paymentPlanRepo.findById(Long.valueOf(id)).orElseThrow(
                    ()-> new PaymentResourceNotFoundException("No Payment Method associated with the given Payment Plan",
                    HttpStatus.NOT_FOUND));
            paymentMethodRepo.findAll().stream().filter(Objects::nonNull)
                    .filter(paymentMethod -> paymentMethod.getPaymentPlans()!=null &&
                            paymentMethod.getPaymentPlans().stream()
                                    .anyMatch(paymentPlan ->
                                            paymentPlan.getPaymentPlanId().equals(Long.valueOf(id)))).
                    map(paymentMethod -> {
                PaymentMethodDto paymentMethodDto = new PaymentMethodDto();
                BeanUtils.copyProperties(paymentMethod,paymentMethodDto);
                return  paymentMethodDto;
            }).forEach(paymentMethodDtos::add);
            return ResponseEntity.status(HttpStatus.OK).body(paymentMethodDtos);
        }catch (Exception exception){
            throw new InternalServerException("Error while fetching payment method", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
