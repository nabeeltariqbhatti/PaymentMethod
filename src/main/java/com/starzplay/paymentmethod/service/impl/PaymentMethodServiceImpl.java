package com.starzplay.paymentmethod.service.impl;

import com.starzplay.paymentmethod.constants.Constants;
import com.starzplay.paymentmethod.dto.PaymentMethodDto;
import com.starzplay.paymentmethod.dto.PaymentPlanDto;
import com.starzplay.paymentmethod.entity.PaymentMethod;
import com.starzplay.paymentmethod.entity.PaymentPlan;
import com.starzplay.paymentmethod.exception.InternalServerException;
import com.starzplay.paymentmethod.exception.PaymentResourceNotFoundException;
import com.starzplay.paymentmethod.repo.PaymentMethodRepo;
import com.starzplay.paymentmethod.service.PaymentMethodService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.net.URI;
import java.util.LinkedList;
import java.util.List;

/**
 * @author: Nabeel Tariq Bhatti
 * @created: 2023/02/07
 */
@Service
public class PaymentMethodServiceImpl implements PaymentMethodService {

    private final PaymentMethodRepo paymentMethodRepo;

    @Autowired
    public PaymentMethodServiceImpl(PaymentMethodRepo paymentMethodRepo) {
        this.paymentMethodRepo = paymentMethodRepo;
    }

    @Override
    public ResponseEntity<? extends Object> getAllPaymentMethods() {
        List<PaymentMethodDto>  paymentMethodDtos = new LinkedList<>();
        try{
            paymentMethodRepo.findAll().stream().map(paymentMethod -> {
                PaymentMethodDto paymentMethodDto = new PaymentMethodDto();
                BeanUtils.copyProperties(paymentMethod,paymentMethodDto);
                return  paymentMethodDto;
            }).forEach(paymentMethodDtos::add);
            return  ResponseEntity.status(HttpStatus.OK).body(paymentMethodDtos);
        }catch (Exception exception){
            throw new InternalServerException("Error while fetching payment method", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<? extends Object> getByPaymentMethodName(String name) {

        List<PaymentMethodDto>  paymentMethodDtos = new LinkedList<>();
        try{
            paymentMethodRepo.findPaymentMethodByName(name).stream().map(paymentMethod -> {
                PaymentMethodDto paymentMethodDto = new PaymentMethodDto();
                BeanUtils.copyProperties(paymentMethod,paymentMethodDto);
                return  paymentMethodDto;
            }).forEach(paymentMethodDtos::add);
            return ResponseEntity.status(HttpStatus.OK).body(paymentMethodDtos);
        }catch (Exception exception){
            throw new InternalServerException("Error while fetching payment method", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }




    @Override
    public ResponseEntity<PaymentMethodDto> updatePaymentMethod(Long id, PaymentMethodDto paymentMethod) {
        PaymentMethod paymentMethodDb = paymentMethodRepo.findById(id).orElseThrow(()-> new PaymentResourceNotFoundException("No PaymentMethod found with this id ", HttpStatus.NO_CONTENT));

        try{
            for (PaymentPlanDto paymentPlan : paymentMethod.getPaymentPlans()) {
                PaymentPlan paymentPlanEntity = new PaymentPlan();
                BeanUtils.copyProperties(paymentPlan,paymentPlanEntity);
                paymentMethodDb.getPaymentPlans().add(paymentPlanEntity);
                paymentPlanEntity.getPaymentMethods().add(paymentMethodDb);
            }
              paymentMethodRepo.save(paymentMethodDb);
            return ResponseEntity.noContent().build();
        }catch (Exception exception){
            exception.printStackTrace();
            throw new InternalServerException("Error while updating payment method", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> deletePaymentMethod(Long id) {
        PaymentMethod paymentMethod = paymentMethodRepo.findById(id).orElseThrow(()->
                new PaymentResourceNotFoundException(String.format("No payment method having id %s found", id),
                        HttpStatus.NOT_FOUND));
        paymentMethodRepo.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<? extends Object> createPaymentMethod(PaymentMethodDto paymentMethodDto) {
        if( paymentMethodDto!=null &&!StringUtils.hasText(paymentMethodDto.getName())){
            return ResponseEntity.badRequest().build();
        }
        try{
            PaymentMethod paymentMethod = new PaymentMethod();
            BeanUtils.copyProperties(paymentMethodDto,paymentMethod);
            PaymentMethod save = paymentMethodRepo.save(paymentMethod);
            return ResponseEntity
                    .created(
                    URI.create(String.format(Constants.BASE_ADDRESS_API_PAYMENT_METHOD.concat("/")+"%d",paymentMethod.getId()))).body(paymentMethod);
        }catch (Exception exception){
            throw new InternalServerException("Error while fetching payment method", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    public ResponseEntity<? extends Object> getPaymentMethod(Long id) {
        try{
            return ResponseEntity.ok(paymentMethodRepo.findById(id));
        }catch (Exception exception){
            throw new InternalServerException("Error while fetching payment method", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
