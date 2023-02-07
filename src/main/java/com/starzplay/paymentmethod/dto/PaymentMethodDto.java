package com.starzplay.paymentmethod.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.starzplay.paymentmethod.constants.PaymentType;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author: Nabeel Tariq Bhatti
 * @created: 2023/02/07
 */
@Getter
@Setter
@JsonRootName("paymentMethods")
public class PaymentMethodDto {

    @JsonIgnore
    private Long id;

    private String name;
    private String displayName;
    private PaymentType paymentType;
    private List<PaymentPlanDto> paymentPlans;




}
