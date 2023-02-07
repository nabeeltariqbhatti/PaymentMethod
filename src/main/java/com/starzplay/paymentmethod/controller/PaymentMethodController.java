package com.starzplay.paymentmethod.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.starzplay.paymentmethod.constants.Constants;
import com.starzplay.paymentmethod.dto.PaymentMethodDto;
import com.starzplay.paymentmethod.service.PaymentMethodService;
import com.starzplay.paymentmethod.service.PaymentPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    /**
     Get all payment methods
     @return ResponseEntity containing list of payment method objects
     */
    @GetMapping
    public ResponseEntity<? extends Object> getAllPaymentMethods(){
        return paymentMethodService.getAllPaymentMethods();
    }

    /**
     Get payment method by name
     @param name String name of payment method to retrieve
     @return ResponseEntity containing payment method object
     */
    @GetMapping(params = {"name"})
    public ResponseEntity<? extends Object> getByPaymentMethodName(@RequestParam(name="name")String name ){
            return paymentMethodService.getByPaymentMethodName(name);
    }

    /**
     *Get payment method by id
     * @pathvariable id Long id of payment method to retrieve
     * @return ResponseEntity containing payment method object
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<? extends Object> getPaymentMethod(@PathVariable(name="id")Long id ){
        return paymentMethodService.getPaymentMethod(id);
    }

    /**
     * Get payment plan by id
     * @param id String id of payment plan to retrieve
     * @return ResponseEntity containing payment plan object
     */
    @GetMapping(params = {"id"})
    public ResponseEntity<? extends Object> getByPaymentMethodByPaymentPlanId(@RequestParam(name="id")String id ){
        return paymentPlanService.getByPaymentMethodByPaymentPlanId(id);
    }

    /**
     * Create new payment method
     * @param paymentMethodDto PaymentMethodDto object containing payment method information to create
     * @return ResponseEntity containing newly created payment method object
     *
     *
     */
    @PostMapping
    public ResponseEntity<? extends Object> createPaymentMethod(@RequestBody PaymentMethodDto paymentMethodDto){
        return paymentMethodService.createPaymentMethod(paymentMethodDto);
    }

    /**
     *Update existing payment method
     * @param id Long id of payment method to update
     * @param paymentMethod PaymentMethodDto object containing updated payment method information
     * @return ResponseEntity containing updated payment method object
     * /
     */
    @PutMapping("/{id}")
    ResponseEntity<PaymentMethodDto> updatePaymentMethod(@PathVariable Long id, @RequestBody PaymentMethodDto paymentMethod) throws JsonProcessingException {
        System.out.println(new ObjectMapper().writeValueAsString(paymentMethod));
        return paymentMethodService.updatePaymentMethod(id,paymentMethod);
    }

    /**
     *
     * Delete payment method by id
     * @param id Long id of payment method to delete
     * @return ResponseEntity indicating success or failure of operation
     * */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePaymentMethod(@PathVariable Long id){
        return paymentMethodService.deletePaymentMethod(id);
    }
}
