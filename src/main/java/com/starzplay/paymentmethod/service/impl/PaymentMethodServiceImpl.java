package com.starzplay.paymentmethod.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.starzplay.paymentmethod.Utils.Util;
import com.starzplay.paymentmethod.constants.Constants;
import com.starzplay.paymentmethod.dto.PaymentMethodDto;
import com.starzplay.paymentmethod.dto.PaymentMethodResponse;
import com.starzplay.paymentmethod.entity.PaymentMethod;
import com.starzplay.paymentmethod.exception.InternalServerException;
import com.starzplay.paymentmethod.exception.PaymentResourceNotFoundException;
import com.starzplay.paymentmethod.repo.PaymentMethodRepo;
import com.starzplay.paymentmethod.repo.PaymentPlanRepo;
import com.starzplay.paymentmethod.service.PaymentMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.net.InetAddress;
import java.net.URI;
import java.util.List;

/**
 * @author: Nabeel Tariq Bhatti
 * @created: 2023/02/07
 */
@Service
public class PaymentMethodServiceImpl implements PaymentMethodService {

    private final PaymentMethodRepo paymentMethodRepo;

    private final PaymentPlanRepo paymentPlanRepo;
    private final ObjectMapper objectMapper;
    @Autowired
    public PaymentMethodServiceImpl(PaymentMethodRepo paymentMethodRepo, PaymentPlanRepo paymentPlanRepo, ObjectMapper objectMapper, Util utils) {
        this.paymentMethodRepo = paymentMethodRepo;
        this.paymentPlanRepo = paymentPlanRepo;
        this.objectMapper = objectMapper;
    }

    @Override
    public ResponseEntity<? extends Object> getAllPaymentMethods() {
        try{
            List<PaymentMethodDto> paymentMethodsDtos = Util.mapPaymentMethodsToPaymentMethodsDto(paymentMethodRepo.findAll(),objectMapper);
            PaymentMethodResponse paymentMethodResponse = new PaymentMethodResponse();
            paymentMethodResponse.setPaymentMethods(paymentMethodsDtos);
            return  ResponseEntity.status(HttpStatus.OK).body(paymentMethodResponse);
        }catch (Exception exception){
            throw new InternalServerException("Error while fetching payment method", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @Override
    public ResponseEntity<? extends Object> getByPaymentMethodName(String name) {
        try{
            List<PaymentMethodDto> paymentMethodDtos = Util.mapPaymentMethodsToPaymentMethodsDto(paymentMethodRepo.findPaymentMethodByName(name), objectMapper);
            PaymentMethodResponse paymentMethodResponse = new PaymentMethodResponse();
            paymentMethodResponse.setPaymentMethods(paymentMethodDtos);
            return ResponseEntity.status(HttpStatus.OK).body(paymentMethodDtos);
        }catch (Exception exception){
            throw new InternalServerException("Error while fetching payment method", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }




    @Override
    public ResponseEntity<PaymentMethodDto> updatePaymentMethod(Long id, PaymentMethodDto paymentMethod) {
        PaymentMethod paymentMethodDb = paymentMethodRepo.findById(id).orElseThrow(() ->
                new PaymentResourceNotFoundException("No PaymentMethod found with this id ", HttpStatus.NO_CONTENT));
        PaymentMethod paymentMethodNew = objectMapper.convertValue(paymentMethod, PaymentMethod.class);
        paymentMethodNew.setId(id);
        try {
            if(paymentMethodNew.getPaymentPlans()==null){
                paymentMethodNew.setPaymentPlans(new ArrayList<>());
            }
            /**
             * cascade type all
             */
            Iterator<PaymentPlan> iterator = paymentMethodDb.getPaymentPlans().iterator();
            while (iterator.hasNext()){
                for (PaymentPlanDto paymentPlan : paymentMethod.getPaymentPlans()) {
                    if(paymentPlan.getPaymentPlanId()!=null &&
                            paymentPlan.getPaymentPlanId().equals(iterator.next().getPaymentPlanId())){
                        paymentPlanRepo.findById(paymentPlan.getPaymentPlanId()).orElseThrow(()->
                                new PaymentResourceNotFoundException("no paymentplan having id " + paymentPlan.getPaymentPlanId(),
                                        HttpStatus.NOT_FOUND));
                        iterator.remove();
                        break;
                    }
                }
            }
            paymentMethodNew.getPaymentPlans().addAll(paymentMethodDb.getPaymentPlans());
            paymentMethodNew = paymentMethodRepo.save(paymentMethodNew);
            return ResponseEntity.accepted().build();
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
            PaymentMethod paymentMethod = objectMapper.convertValue(paymentMethodDto,PaymentMethod.class);
            PaymentMethod save = paymentMethodRepo.save(paymentMethod);
            InetAddress inetAddress = InetAddress.getLocalHost();
            String hostAddress = inetAddress.getHostAddress();
            return ResponseEntity
                    .created(
                    URI.create(String.format(hostAddress+Constants.BASE_ADDRESS_API_PAYMENT_METHOD.concat("/")+"%d",paymentMethod.getId()))).body(paymentMethod);
        }catch (Exception exception){
            throw new InternalServerException("Error while fetching payment method", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    public ResponseEntity<? extends Object> getPaymentMethod(Long id) {
        try{
            return ResponseEntity.ok(paymentMethodRepo.findById(id).get());
        }catch (Exception exception){
            throw new InternalServerException("Error while fetching payment method", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
