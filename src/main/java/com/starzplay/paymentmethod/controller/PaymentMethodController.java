package com.starzplay.paymentmethod.controller;

import com.starzplay.paymentmethod.constants.Constants;
import com.starzplay.paymentmethod.dto.PaymentMethodDto;
import com.starzplay.paymentmethod.service.PaymentMethodService;
import com.starzplay.paymentmethod.service.PaymentPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * @author: Nabeel Tariq Bhatti
 * @created: 2023/02/07
 */
@RestController
@RequestMapping(path= Constants.BASE_ADDRESS_API_PAYMENT_METHOD)
public class PaymentMethodController {

    private final PaymentMethodService paymentMethodService;
    private final PaymentPlanService paymentPlanService;

    @Autowired
    public PaymentMethodController(PaymentMethodService paymentMethodService, PaymentPlanService paymentPlanService) {
        this.paymentMethodService = paymentMethodService;
        this.paymentPlanService = paymentPlanService;
    }


    @GetMapping
    public ResponseEntity<? extends Object> getAllPaymentMethods(){
        return paymentMethodService.getAllPaymentMethods();
    }

    @GetMapping
    public ResponseEntity<? extends Object> getByPaymentMethodName(@RequestParam(name="name")String name ){
            return paymentMethodService.getByPaymentMethodName(name);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<? extends Object> getPaymentMethod(@PathVariable(name="id")Long id ){
        return paymentMethodService.getPaymentMethod(id);
    }
    @GetMapping
    public ResponseEntity<? extends Object> getByPaymentMethodByPaymentPlanId(@RequestParam(name="id")String id ){
        return paymentPlanService.getByPaymentMethodByPaymentPlanId(id);
    }

    @PostMapping
    public ResponseEntity<? extends Object> createPaymentMethod(@RequestBody PaymentMethodDto paymentMethodDto){
        return paymentMethodService.createPaymentMethod(paymentMethodDto);
    }
    @PutMapping("/{id}")
    ResponseEntity<PaymentMethodDto> updatePaymentMethod(@PathVariable Long id, @RequestBody PaymentMethodDto paymentMethod){
        return paymentMethodService.updatePaymentMethod(id,paymentMethod);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePaymentMethod(@PathVariable Long id){
        return paymentMethodService.deletePaymentMethod(id);
    }
}
