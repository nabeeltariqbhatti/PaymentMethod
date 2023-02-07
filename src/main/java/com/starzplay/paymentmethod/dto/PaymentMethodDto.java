package com.starzplay.paymentmethod.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.starzplay.paymentmethod.constants.PaymentType;
import com.starzplay.paymentmethod.entity.PaymentPlan;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

/**
 * @author: Nabeel Tariq Bhatti
 * @created: 2023/02/07
 */
@Getter
@Setter
public class PaymentMethodDto {

    private Long id;

    private String name;
    private String displayName;
    private PaymentType paymentType;
    private Set<PaymentPlanDto> paymentPlans;




}
